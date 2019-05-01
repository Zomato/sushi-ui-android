module.exports = {
  title: 'Sushi UI Kit for Android',
  description: 'A delicious UI Kit to build Android apps. Made with ❤ by Zomato',
  themeConfig: {
    nav: [{ text: 'Guide', link: '/pages/' }],
    sidebarDepth: 2,
    sidebar: [
      '/pages/',
      {
        title: 'Resources',
        children: ['/pages/01-resources/Typography.md']
      },
      {
        title: 'Atoms',
        children: ['/pages/02-atoms/SushiTextView.md', '/pages/02-atoms/SushiButton.md']
      },
      {
        title: 'Molecules',
        children: []
      },
      {
        title: 'Organisms',
        children: []
      }
    ]
  }
}
