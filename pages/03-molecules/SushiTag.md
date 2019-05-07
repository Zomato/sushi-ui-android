# SushiTag

<p align="center">
<img style="width: 66%" src="../../img/tags/top-tag.png">
</p>

A tag is a compact element to show tiny text based attributes like ratings, ad or offer banners.
It is similar to [Chips](https://material.io/design/components/chips.html), but isn't directly based on that.

## Features

`SushiTag` extends from `SushiTextView` so all properties of that are avaialble here

Additional attributes

| Attribute         | Value                                                                  |
| ----------------- | ---------------------------------------------------------------------- |
| app:tagType       | `rounded` (default), `capsule`, `rounded_outline` or `capsule_outline` |
| app:tagSize       | `large` (default), `medium`, `small` or `tiny`                         |
| app:tagColor      | `@color` variable, sets background or outline color (based on type)    |
| android:textColor | Override text color (default is `white` for colored tags)              |
| app:drawableLeft  | `@drawable` or `@string` which is treated as iconfont character        |
| app:drawableRight | `@drawable` or `@string` which is treated as iconfont character        |
| app:drawableStart | `@drawable` or `@string` which is treated as iconfont character        |
| app:drawableEnd   | `@drawable` or `@string` which is treated as iconfont character        |

:::warning NOTE

- Font weight is medium for large tags, and regular for all other tags
- Tiny tags have only `1dp` padding around the text

:::

## Usage

### Create in XML

```xml
<com.zomato.sushilib.molecules.tags.SushiTag
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="AD"
    app:tagColor="@color/sushi_grey_800"
    app:tagSize="tiny"
    app:tagType="rounded" />
```

### Functionality in Java/Kotlin

```kotlin
// Size and type can be changed at runtime
tag.tagType = TagType.ROUNDED
tag.tagSize = TagSize.MEDIUM
tag.tagColor = Color.GREEN

// This is for example, in practice set a proper color state list
// with different colors of enabled and disabled states
tag.setTextColor = (ColorStateList.valueOf(BLUE))
```

## Examples

### Sizes

![tag sizes](../../img/tags/sizes.png)

### Fill Styles and Drawables

![tag styles](../../img/tags/styles.png)
