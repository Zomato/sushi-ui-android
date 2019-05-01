# Typography

We support the following typeface variations.

- ExtraLight
- Light
- Regular
- Medium
- SemiBold

You may use any Font that has 6 font weights and assign them these aliases.
While we have Metropolis, Okra and Roboto for demo, you can use any font that goes with your brand.

## Setting your own Font Family

### 1. Add fonts
Add all your `ttf` files into the `res/fonts` folder.

### 2. Create a `ThemeOverlay` for your font.

Here is an example for Okra

```xml
    <style name="ThemeOverlay.OkraFont" parent="">
        <item name="fontFamilyThin">@font/okra_thin</item>
        <item name="fontFamilyExtraLight">@font/okra_extralight</item>
        <item name="fontFamilyLight">@font/okra_light</item>
        <item name="fontFamilyRegular">@font/okra_regular</item>
        <item name="fontFamilyMedium">@font/okra_medium</item>
        <item name="fontFamilySemiBold">@font/okra_semibold</item>
    </style>
```

And here is an example for Roboto, which doesn't need any extra `ttf` files
as those are already in Android OS

```xml
    <style name="ThemeOverlay.RobotoFont" parent="">
        <item name="fontFamilyThin">sans-serif-thin</item>
        <item name="fontFamilyExtraLight">sans-serif-thin</item>
        <item name="fontFamilyLight">sans-serif-light</item>
        <item name="fontFamilyRegular">sans-serif</item>
        <item name="fontFamilyMedium">sans-serif-medium</item>
        <item name="fontFamilySemiBold">sans-serif-black</item>
    </style>
```

### 3. Set it in your theme

Set this in your theme

```xml
    <style name="AppTheme" parent="AppBaseTheme">
        <!-- Customize your theme here. -->
        <item name="android:theme">@style/ThemeOverlay.OkraFont</item>
    </style>
```  