# SushiRatingBar

<p align="center">
<img src="../../img/rating/rating-clicks.gif" style="width: 65%" alt="rating clicks">
</p>

A rating bar to show ratings to use, or act as an input element for user to apply rating.
This is similar to Android's `RatingBar`, but **is not based** on it.

## Features

SushiRatingBar is composed of `SushiTag` and thus, some tag properties can be proxied into the internal tags.

| Attribute         | Value                                                           |
| ----------------- | --------------------------------------------------------------- |
| app:tagType       | `capsule` (default) or `rounded`                                |
| app:tagSize       | `large` (default), `medium`, `small` or `tiny`                  |
| app:rating        | rating value. accepts floats, between 0 to 5                    |
| app:drawableLeft  | `@drawable` or `@string` which is treated as iconfont character |
| app:drawableRight | `@drawable` or `@string` which is treated as iconfont character |
| app:drawableStart | `@drawable` or `@string` which is treated as iconfont character |
| app:drawableEnd   | `@drawable` or `@string` which is treated as iconfont character |

:::tip BEHAVIOUR NOTES

- If you set `tagType` to `capsule_outline` or `rounded_outline` that has no effect. That will behave same as `capsule` and `rounded` respectively.
- Default `drawableRight` is a filled star. If you want to remove it (for eg, to set a drawableLeft instead), set `app:drawableRight="@null"`

:::

## Usage

### Create in XML

```xml
<com.zomato.sushilib.organisms.ratings.SushiRatingBar
    app:rating="4"
    app:tagSize="small"
    android:clickable="false"
    app:drawableRight="@string/icon_right_triangle"
    android:layout_marginTop="@dimen/sushi_spacing_mini"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

### Functionality in Java/Kotlin

You can set the rating value (must be integer ranging from 0 to 5)

Also you can enable or disable input by `setClickable`

```kotlin
ratingBar.rating = 4

// Users can tap and set rating now
ratingBar.setClickable(true)
```

## Examples

![rating drawable left](../../img/rating/rating-dr-left.png)

<figcaption align="center">
<small>Setting drawable on left instead of right</small>
</figcaption>

![rating arrow drawable](../../img/rating/rating-icon-change.png)

<figcaption align="center">
<small>Setting different drawable, and changing size</small>
</figcaption>
