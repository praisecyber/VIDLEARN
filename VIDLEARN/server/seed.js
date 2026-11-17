// Course catalog served by the API.
const V = [
  'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4',
  'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4',
  'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4',
  'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4',
  'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4',
];

const esc = s => String(s).replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
const l = (t, task, start, chk, hint, v) => ({ t, task, start, chk, hint, uri: V[v % V.length] });
const D = '(^|\\n)\\s*-.*\\n\\s*-.*\\n\\s*-', D2 = '-.*\\n\\s*-';

const SEED = [
  { id: 1, icon: 'layout', color: '#0b6b3a', cat: 'Design', t: 'Complete Web Design Masterclass 2026', by: 'Amara Okoye', r: 4.8, n: '18,420', p: '₦8,500', bs: 1, d: 'Learn colour, type, layout and motion to design modern websites people love.', L: [
    l('Colour & contrast', 'Make the heading text red and the background black.', '<h1 style="">Hello world</h1>', ['color\\s*:\\s*(red|#0b6b3a)', 'background[a-z-]*\\s*:\\s*(black|#000)'], 'Use style="color:red;background:black"', 0),
    l('Typography that sells', 'Set the font-size to 32px and font-weight to bold.', '<p style="">Big bold text</p>', ['font-size\\s*:\\s*32px', 'font-weight\\s*:\\s*(bold|700)'], 'Add font-size:32px; font-weight:bold;', 1),
    l('Layouts with grid', 'Create a 3 column grid container.', '<div style="">\n<p>1</p><p>2</p><p>3</p>\n</div>', ['display\\s*:\\s*grid', 'grid-template-columns'], 'Use display:grid; grid-template-columns:1fr 1fr 1fr;', 2),
    l('Motion basics', 'Add a CSS transition to the button.', '<button style="">Hover me</button>', ['transition'], 'Add transition: all .3s;', 3)] },
  { id: 2, icon: 'code', color: '#111114', cat: 'Code', t: 'Python for Beginners: Zero to Hero', by: 'Dev Patel', r: 4.7, n: '52,110', p: '₦9,900', bs: 1, d: 'Write real Python programs with simple, step by step video lessons.', L: [
    l('Variables & types', 'Create a variable called name and print it.', '# your code here\n', ['name\\s*=', 'print\\('], "name = 'Ada' then print(name)", 1),
    l('Loops and logic', 'Write a for loop that prints numbers with range.', '# your code here\n', ['for\\s+\\w+\\s+in\\s+range', 'print'], 'for i in range(1, 6): print(i)', 2),
    l('Functions', 'Define a function greet(name) that returns text.', '# your code here\n', ['def\\s+greet\\s*\\(', 'return'], "def greet(name): return 'Hi ' + name", 3),
    l('Build a mini app', 'Ask the user for input and print a greeting.', '# your code here\n', ['input\\s*\\(', 'print\\s*\\('], "name = input('Name? ')", 4),
    l('Next steps', 'Write a comment (starting with #) about what you will build next.', '', ['#\\s*\\w+'], 'Start the line with # and a few words', 0)] },
  { id: 3, icon: 'dollar-sign', color: '#1f8f55', cat: 'Business', t: 'Money & Investing 101', by: 'Chidi Eze', r: 4.6, n: '9,870', p: '₦7,200', d: 'Budget, save and invest with confidence, even if you are starting from scratch.', L: [
    l('Budget that works', 'List 3 monthly expenses, each line starting with -', '- ', [D], 'Write three lines starting with -', 0),
    l('Saving habits', 'Write your goal like: Save 50000 by December', '', ['save\\s+\\S+.*\\bby\\b'], 'Use the words Save ... by ...', 1),
    l('Intro to investing', 'Write the percent of income you would invest, e.g. 20%', '', ['\\d+\\s*%'], 'Include a number with a % sign', 2),
    l('Avoiding scams', 'List 2 scam red flags, each starting with -', '- ', [D2], 'Two lines starting with -', 3)] },
  { id: 4, icon: 'film', color: '#0b6b3a', cat: 'Creative', t: 'Video Editing Fast: Reels, Shorts & More', by: 'Lena Cruz', r: 4.5, n: '6,300', p: '₦6,500', d: 'Cut, grade and export scroll-stopping videos in record time.', L: [
    l('Cuts & pacing', 'Write a shot list of 3 shots, each starting with -', '- ', [D], 'Three lines starting with -', 4),
    l('Sound design', 'Name a sound effect or music style you would add.', '', ['whoosh|sfx|music|beat|ambient|impact'], 'Try: whoosh, beat, ambient', 0),
    l('Colour grading', 'Pick a grade: warm, cool or cinematic.', '', ['warm|cool|cinematic'], 'Type one of the three', 1),
    l('Export for socials', 'Write the Reels export size: 1080x1920', '', ['1080\\s*x\\s*1920'], 'Width x height in pixels', 2)] },
  { id: 5, icon: 'cpu', color: '#111114', cat: 'Code', t: 'AI Tools for Work & Business', by: 'Sam Adeyemi', r: 4.9, n: '31,005', p: '₦10,500', bs: 1, d: 'Use AI to write, research and automate the boring parts of your job.', L: [
    l('Prompting basics', 'Write a prompt starting with: Act as', '', ['^\\s*act as\\s+\\w+'], 'Begin with Act as a ...', 3),
    l('Automate boring tasks', 'Write one task you would automate: Automate ...', '', ['automate\\s+\\w+'], 'Start with the word Automate', 4),
    l('AI for research', 'Write one research question ending with ?', '', ['\\w+.*\\?'], 'End with a question mark', 0)] },
  { id: 6, icon: 'mic', color: '#1f8f55', cat: 'Business', t: 'Public Speaking Confidence', by: 'Grace Bello', r: 4.4, n: '4,120', p: '₦5,900', d: 'Beat nerves, tell better stories and own any room.', L: [
    l('Beat the nerves', 'Write 3 calming steps, each starting with -', '- ', [D], 'Three lines starting with -', 1),
    l('Story structure', 'Write a story opening that uses the word Once', '', ['once'], 'Once upon...', 2),
    l('Body language', 'Write one tip using eye contact or posture.', '', ['eye contact|posture'], 'Mention eye contact or posture', 3),
    l('Q&A mastery', 'Write a Q: and an A: for a question you expect.', 'Q: \nA: ', ['q\\s*:\\s*\\S', 'a\\s*:\\s*\\S'], 'Fill in after Q: and A:', 4)] },
];

const buildLessons = rows => rows.map((r, i) => l(
  String(r.title).trim(),
  (r.task || '').trim() || 'Write something about this lesson.',
  '',
  [r.word ? esc(String(r.word).trim()) : '\\w+'],
  r.word ? 'Include the word: ' + String(r.word).trim() : 'Write anything',
  i,
));

module.exports = { SEED, buildLessons };
