const sidebar = require('../sidebar');
module.exports = {
  title: 'Sushi UI Kit for Android',
  description:
    'A delicious UI Kit to build Android apps. Made with ❤ by Zomato',
  base: '/Sushi-Android/',
  themeConfig: {
    nav: [
      { text: 'Guide', link: '/pages/' },
      { text: 'Blog', link: 'https://www.zomato.com/blog/sushi' },
      { text: 'Github', link: 'https://github.com/Zomato/Sushi-Android' }
    ],
    sidebarDepth: 2,
    sidebar
  }
};
