# SushiCheckBox

A form component for selecting multiple options in a list. Based on [AppCompatCheckBox](https://developer.android.com/reference/android/support/v7/widget/AppCompatCheckBox)

## Features

Supports all features of [AppCompatCheckBox](https://developer.android.com/reference/android/support/v7/widget/AppCompatCheckBox)

| Attribute        | Value                                                   |
| ---------------- | ------------------------------------------------------- |
| app:controlColor | A `@color` uses to tint and shade enabled/checked state |

## Usage

### Create in XML

```xml
<com.zomato.sushilib.molecules.inputfields.SushiCheckBox
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Pepsi" />

<com.zomato.sushilib.molecules.inputfields.SushiCheckBox
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Americano"
    app:controlColor="@color/sushi_yellow_500" />
```

### Functionality in Java/Kotlin

Apart from default functionality, you can change control color

```kotlin
checkBox.isChecked = true
checkBox.controlColor = Color.RED
```

## Examples

![checkbox](../../img/compoundbtn/checkbox.png)
