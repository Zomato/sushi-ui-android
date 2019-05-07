# SushiTextView

![text-medium](../../img/textviews/text-medium.png)

A view that allows showing text to the user. Can use any of the typographies supported by _Sushi_
This builds upon Android Support Library's [AppCompatTextView](https://developer.android.com/reference/android/support/v7/widget/AppCompatTextView)

## Features

Supports all the features of `AppCompatTextView`. Additionally, we support
font-icon based drawables, on left (start) and right (end).

| Attribute          | Value                                                              |
| ------------------ | ------------------------------------------------------------------ |
| app:textFontWeight | `extralight`, `light`, `regular` (default), `medium` or `semibold` |
| app:drawableLeft   | `@drawable` or `@string` which is treated as iconfont character    |
| app:drawableRight  | `@drawable` or `@string` which is treated as iconfont character    |
| app:drawableStart  | `@drawable` or `@string` which is treated as iconfont character    |
| app:drawableEnd    | `@drawable` or `@string` which is treated as iconfont character    |

<DrawableWarning/>

## Usage

### Creating in XML

```xml
<com.zomato.sushilib.atoms.textviews.SushiTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Text listing"
    android:textSize="@dimen/sushi_textsize_700"
    app:textFontWeight="extralight"
    app:drawableLeft="@string/icon_unfilled_star" />
```

### Changing properties in Java/Kotlin

```kotlin
myTextView.setTextAppearance(R.style.TextAppearance_Sushi_Label)

// Setting a tint (via a ColorStateList) of the drawables
myTextView.setCompoundDrawableTintList(ColorStateList.valueOf(Color.RED))
// Ideally set a proper ColorStateList that supports disabled/enabled modes etc
```

## Examples

### Weights

![text-semibold](../../img/textviews/text-semibold.png)
![text-medium](../../img/textviews/text-medium.png)

### Drawable

![text-with-drawable](../../img/textviews/text-with-drawable.png)
