# SushiRadioButton

A form component for selecting one out of multiple options. Based on [AppCompatRadioButton](https://developer.android.com/reference/android/support/v7/widget/AppCompatRadioButton)

## Features

Supports all features of [AppCompatRadioButton](https://developer.android.com/reference/android/support/v7/widget/AppCompatRadioButton)

Additional attributes -

| Attribute        | Value                                                   |
| ---------------- | ------------------------------------------------------- |
| app:controlColor | A `@color` uses to tint and shade enabled/checked state |

## Usage

### Create in XML

```xml
<RadioGroup
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <com.zomato.sushilib.molecules.inputfields.SushiRadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="These show up on your mind" />

    <com.zomato.sushilib.molecules.inputfields.SushiRadioButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="These show up in your phone"
        app:controlColor="@color/sushi_yellow_500" />
</RadioGroup>
```

### Functionality in Java/Kotlin

Apart from default functionality, you can change control color

```kotlin
radioButton.isChecked = true
radioButton.controlColor = Color.RED
```

## Examples

![radio](../../img/compoundbtn/radio.png)
