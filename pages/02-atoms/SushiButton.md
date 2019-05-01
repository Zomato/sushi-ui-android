# SushiButton

A view that allows users to tap to trigger an action. `SushiButton` is fully based on
[MaterialButton](https://material.io/develop/android/components/material-button/)

## Features

SushiButton comes with following properties

| Attribute     | Value                                                           |
| ------------- | --------------------------------------------------------------- |
| buttonSize    | `large,`medium`or`small`                                        |
| buttonType    | `solid`, `outline` or `text`                                    |
| buttonColor   | And @color/ attribute that we use to theme the button           |
| drawableLeft  | `@drawable` or `@string` which is treated as iconfont character |
| drawableRight | `@drawable` or `@string` which is treated as iconfont character |
| drawableStart | `@drawable` or `@string` which is treated as iconfont character |
| drawableEnd   | `@drawable` or `@string` which is treated as iconfont character |

:::warning NOTE
For all the four drawable properties, use `app:drawableLeft` and **never** `android:drawableLeft`.
:::

### Unsupported Properties

Do not set `textSize`, `textColor` and `background` manually. Rely only on above
properties to set a proper button theme for you.

Behaviour with `drawableTop` and `drawableBottom` is also unspecified

Setting unsupported properties _may_ throw **UnsupportedException** in future

## Usage

### Creating in XML

```xml
  <com.zomato.sushilib.atoms.buttons.SushiButton
      app:buttonType="solid"
      app:buttonSize="large"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="Large Button" />

```

### Changing Properties Programatically

```kotlin
sushiButton.setButtonType(ButtonType.SOLID)
sushiButton.setButtonSize(ButtonSize.LARGE)
```

## Types

You can set button type using either XML in layouts, or programatically in Java/Kotlin

### Solid

![solid-button](../../img/buttons/btnsolid.png)

### Outline

![outline-button](../../img/buttons/btnoutline.png)

### Text

![text-button](../../img/buttons/btntext.png)

## Sizes

### Large

![solid-button](../../img/buttons/btnsolidlarge.png)

### Medium

![solid-button](../../img/buttons/btnsolid.png)

### Small

![solid-button](../../img/buttons/btnsolidsmall.png)

## Colors

We support using any color (we set the text color, the disabled state tint lists accordingly).

![button-colors](../../img/buttons/btncolors.png)
