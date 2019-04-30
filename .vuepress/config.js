module.exports = {
  title: 'Sushi UI Kit for Android',
  description: 'A delicious UI Kit to build Android apps. Made with ❤ by Zomato',
  themeConfig: {
    nav: [{ text: 'Guide', link: '/pages/' }],
    sidebar: [
      '/pages/',
      {
        title: 'Atoms',
        collapsible: false,
        children: ['/pages/atoms/Typography.md']
      },
      {
        title: 'Molecules',
        collapsible: false,
        children: []
      },
      {
        title: 'Organisms',
        collapsible: false,
        children: []
      }
    ]
  }
}
