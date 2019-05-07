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
        children: ['/pages/01-resources/Typography.md', '/pages/01-resources/Colors.md']
      },
      {
        title: 'Atoms',
        children: [
          '/pages/02-atoms/SushiTextView.md',
          '/pages/02-atoms/SushiButton.md',
          '/pages/02-atoms/SushiCircleImageView.md',
          '/pages/02-atoms/SushiRoundedImageView.md'
        ]
      },
      {
        title: 'Molecules',
        children: [
          'pages/03-molecules/SushiTextInputField.md',
          'pages/03-molecules/SushiSwitch.md',
          'pages/03-molecules/SushiRadioButton.md',
          'pages/03-molecules/SushiCheckBox.md'
        ]
      },
      {
        title: 'Organisms',
        children: []
      }
    ]
  }
}
