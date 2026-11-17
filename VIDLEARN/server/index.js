require('dotenv').config();
const { app } = require('./app');
const { isWritable, FILE } = require('./store');

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
  console.log(`Vidlearn Secure Backend API listening on http://localhost:${PORT}/api`);
  console.log(`Environment: ${process.env.NODE_ENV || 'development'}`);
  console.log(`Data file: ${FILE}${isWritable() ? '' : ' (read-only, keeping data in memory)'}`);
});
