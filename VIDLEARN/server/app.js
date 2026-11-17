const crypto = require('crypto');
const path = require('path');
const express = require('express');
const cors = require('cors');
const { Course, User, sessions, scenarios } = require('./store');
const { buildLessons } = require('./seed');

const CATS = ['Design', 'Code', 'Business', 'Creative'];
const hash = (pw, salt) => crypto.scryptSync(pw, salt, 64).toString('hex');
const sameHash = (a, b) => {
  const x = Buffer.from(a, 'hex'), y = Buffer.from(b, 'hex');
  return x.length === y.length && crypto.timingSafeEqual(x, y);
};

const shapeMe = u => {
  const mine = {}, done = {}, code = {}, notes = {}, favorites = {};
  Object.keys(u.enrolled || {}).forEach(cid => { mine[cid] = 1; });
  Object.entries(u.lessons || {}).forEach(([k, v]) => {
    if (v.done) done[k] = true;
    if (v.code) code[k] = v.code;
    if (v.note) notes[k] = v.note;
  });
  Object.keys(u.favorites || {}).forEach(id => { favorites[id] = true; });
  return { user: u.name, email: u.email, mine, done, code, notes, favorites };
};

const auth = async (req, res, next) => {
  const token = (req.headers.authorization || '').replace(/^Bearer\s+/i, '');
  const userId = token && sessions[token];
  const user = userId && await User.findOne({ id: userId });
  if (!user) return res.status(401).json({ error: 'Please log in again' });
  req.user = user;
  next();
};

const router = express.Router();

router.get('/health', async (req, res) => {
  try {
    const courseCount = await Course.countDocuments();
    const userCount = await User.countDocuments();
    res.json({ ok: true, database: 'MongoDB', courses: courseCount, users: userCount });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.get('/courses', async (req, res) => {
  try {
    const cat = (req.query.cat || '').trim();
    const q = (req.query.q || '').trim().toLowerCase();
    const courses = await Course.find({});
    const list = courses.filter(c =>
      (!cat || cat === 'All' || c.cat === cat) &&
      (!q || (c.t + ' ' + c.by).toLowerCase().includes(q)));
    res.json({ courses: list, categories: CATS, total: courses.length });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.get('/practice/scenarios', (req, res) => {
  res.json({ scenarios });
});

router.post('/practice/respond', (req, res) => {
  const scenario = scenarios.find(s => s.id === req.body.scenarioId);
  if (!scenario) return res.status(404).json({ error: 'Scenario not found' });
  const message = req.body.message || '';
  const turn = Math.max(0, Number(req.body.turn) || 0);
  const lower = message.toLowerCase();
  const hasEmpathy = /\b(understand|sorry|hear|appreciate|thanks|thank you)\b/.test(lower);
  const hasSpecific = /\b(example|because|first|next|result|learned|measure|goal|step|plan)\b/.test(lower) || message.length > 110;
  const feedback = [];
  feedback.push(hasEmpathy
    ? 'Good rapport: your response acknowledges the other person.'
    : 'Try opening with an acknowledgment so the other person feels heard.');
  feedback.push(hasSpecific
    ? 'You supported your point with a concrete detail.'
    : 'Add one specific example, action, or next step to make your answer clearer.');

  res.json({
    reply: scenario.prompts[Math.min(turn, scenario.prompts.length - 1)],
    feedback,
    score: 60 + (hasEmpathy ? 20 : 0) + (hasSpecific ? 20 : 0),
    nextTurn: turn + 1,
    complete: turn + 1 >= scenario.prompts.length,
  });
});

router.get('/courses/:id', async (req, res) => {
  try {
    const c = await Course.findOne({ id: Number(req.params.id) });
    if (!c) return res.status(404).json({ error: 'Course not found' });
    res.json({ course: c });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.post('/auth/login', async (req, res) => {
  try {
    const name = (req.body.name || '').trim();
    const email = (req.body.email || '').trim().toLowerCase();
    const password = req.body.password || '';

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) return res.status(400).json({ error: 'Please enter a valid email' });
    if (password.length < 4) return res.status(400).json({ error: 'Password must be at least 4 characters' });

    let user = await User.findOne({ email });
    let created = false;
    if (!user) {
      if (!name) return res.status(400).json({ error: 'Please enter your name' });
      const salt = crypto.randomBytes(16).toString('hex');
      user = new User({
        id: crypto.randomUUID(),
        name,
        email,
        salt,
        hash: hash(password, salt),
        enrolled: {},
        lessons: {},
        favorites: {}
      });
      await user.save();
      created = true;
    } else if (!sameHash(user.hash, hash(password, user.salt))) {
      return res.status(401).json({ error: 'Wrong password for this email' });
    }

    const token = crypto.randomBytes(24).toString('hex');
    sessions[token] = user.id;
    res.json({ token, created, ...shapeMe(user) });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.get('/me', auth, (req, res) => res.json(shapeMe(req.user)));

router.put('/me/favorites/:id', auth, async (req, res) => {
  try {
    const c = await Course.findOne({ id: Number(req.params.id) });
    if (!c) return res.status(404).json({ error: 'Course not found' });
    req.user.favorites = req.user.favorites || {};
    if (req.body.favorite) req.user.favorites[String(c.id)] = true;
    else delete req.user.favorites[String(c.id)];
    req.user.markModified('favorites');
    await req.user.save();
    res.json(shapeMe(req.user));
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.post('/courses', auth, async (req, res) => {
  try {
    const title = (req.body.title || '').trim();
    const rows = (req.body.lessons || []).filter(r => (r.title || '').trim());
    if (!title) return res.status(400).json({ error: 'Add a course title' });
    if (!rows.length) return res.status(400).json({ error: 'Add at least one lesson' });

    const cat = CATS.includes(req.body.category) ? req.body.category : 'Business';
    const allCourses = await Course.find({});
    const nextId = Math.max(100, ...allCourses.map(c => Number(c.id) || 0)) + 1;
    const course = new Course({
      id: nextId,
      icon: 'book-open',
      color: '#0b6b3a',
      cat,
      t: title,
      by: req.user.name,
      r: 5,
      n: 'New',
      p: (req.body.price || '').trim() || '₦5,000',
      d: (req.body.description || '').trim() || 'A new course.',
      custom: true,
      authorId: req.user.id,
      L: buildLessons(rows),
    });
    await course.save();
    res.status(201).json({ course });
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.post('/me/enroll', auth, async (req, res) => {
  try {
    const c = await Course.findOne({ id: Number(req.body.courseId) });
    if (!c) return res.status(404).json({ error: 'Course not found' });
    req.user.enrolled = req.user.enrolled || {};
    req.user.enrolled[String(c.id)] = 1;
    req.user.markModified('enrolled');
    await req.user.save();
    res.json(shapeMe(req.user));
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

router.put('/me/lessons/:cid/:index', auth, async (req, res) => {
  try {
    const c = await Course.findOne({ id: Number(req.params.cid) });
    if (!c) return res.status(404).json({ error: 'Course not found' });
    const index = Number(req.params.index);
    if (!Number.isInteger(index) || index < 0 || index >= c.L.length) return res.status(400).json({ error: 'Unknown lesson' });

    const key = `${c.id}-${index}`;
    const entry = (req.user.lessons = req.user.lessons || {})[key] || {};
    if (typeof req.body.code === 'string') entry.code = req.body.code;
    if (typeof req.body.note === 'string') entry.note = req.body.note;
    if (typeof req.body.done === 'boolean') entry.done = req.body.done;
    req.user.lessons[key] = entry;
    req.user.markModified('lessons');
    await req.user.save();
    res.json(shapeMe(req.user));
  } catch (e) {
    res.status(500).json({ error: e.message });
  }
});

const app = express();
app.use(cors());
app.use(express.json({ limit: '1mb' }));
app.use(express.static(path.join(__dirname, 'public')));
app.use('/api', router);
app.use((req, res) => res.status(404).json({ error: 'Not found' }));

module.exports = { app, router, shapeMe };
