const mongoose = require('mongoose');
const { SEED } = require('./seed');

const MONGO_URI = process.env.MONGO_URI || process.env.MONGODB_URI || 'mongodb://localhost:27017/vidlearn';

const courseSchema = new mongoose.Schema({
  id: { type: Number, unique: true, required: true },
  icon: String,
  color: String,
  cat: String,
  t: String,
  by: String,
  r: Number,
  n: String,
  p: String,
  bs: Number,
  d: String,
  L: Array,
  custom: Boolean,
  authorId: String,
  createdAt: { type: Date, default: Date.now }
});

const userSchema = new mongoose.Schema({
  id: { type: String, unique: true, required: true },
  name: String,
  email: { type: String, unique: true, required: true },
  salt: String,
  hash: String,
  enrolled: { type: Object, default: {} },
  lessons: { type: Object, default: {} },
  favorites: { type: Object, default: {} },
  createdAt: { type: Date, default: Date.now }
});

const Course = mongoose.model('Course', courseSchema);
const User = mongoose.model('User', userSchema);

const sessions = {};

const connectDB = async () => {
  try {
    await mongoose.connect(MONGO_URI);
    console.log('Connected to MongoDB successfully');

    const count = await Course.countDocuments();
    if (count === 0) {
      await Course.insertMany(SEED);
      console.log('Seeded default courses into MongoDB');
    }
  } catch (err) {
    console.error('MongoDB connection error:', err.message);
  }
};

connectDB();

const scenarios = [
  {
    id: "client-scope",
    title: "Handle a Scope Change",
    persona: "Client (Tunde)",
    topic: "Freelance & Consulting",
    level: "Intermediate",
    color: "#0b6b3a",
    icon: "briefcase",
    opening: "Hi! Thanks for the update. By the way, can we also add a live chat feature and user dashboards before launching next week?",
    prompts: [
      "Thanks for sharing that idea! To make sure we hit next week's launch date, should we scope the live chat for Phase 2?",
      "Got it. What is the priority between launching on time versus adding the dashboard first?"
    ],
    turnCount: 2
  },
  {
    id: "job-interview",
    title: "Answer 'Tell me about yourself'",
    persona: "Hiring Manager (Sarah)",
    topic: "Career Development",
    level: "Beginner",
    color: "#111114",
    icon: "account-tie",
    opening: "Welcome! Let's get started. Could you walk me through your background and why you're interested in this role?",
    prompts: [
      "That's a great summary of your past experience. What specific project or achievement are you most proud of?",
      "Impressive! How do you plan to bring those skills into our team's upcoming goals?"
    ],
    turnCount: 2
  },
  {
    id: "salary-negotiation",
    title: "Negotiate Project Rate",
    persona: "Marketing Director (David)",
    topic: "Business & Pricing",
    level: "Advanced",
    color: "#1f8f55",
    icon: "cash-multiple",
    opening: "We love your proposal, but our budget for this video campaign is 20% lower than your quote. Can you match our budget?",
    prompts: [
      "I understand your constraints. If we adjust the deliverables slightly (e.g., 2 revisions instead of 4), we can fit your budget.",
      "Sounds fair! Let's update the contract with those agreed revisions."
    ],
    turnCount: 2
  }
];

module.exports = {
  mongoose,
  Course,
  User,
  sessions,
  scenarios
};
