# Colors

Sushi provides a set of predefined colors in its palette.

For extremely unique cases, feel free to use your own colors, but otherwise, we recommend
using colors from this palette for all components.

## Usage

All colors are defined in resources, you can access them as following

```xml
<!--  Our black and white are slightly off shade
from #000 and #fff for better readability -->
@colors/sushi_black
@colors/sushi_white

<!-- Primary colors have 9 shades and secondary have 5 -->

@colors/sushi_grey_500
@colors/sushi_red_600
@colors/sushi_indigo_100

<!-- ..and so on... -->
```

## Override

Our colors are defined like this

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <color name="sushi_black">#1C1C1C</color>
    <color name="sushi_white">#FFFFFF</color>

    <color name="sushi_grey_100">#F4F4F4</color>
    <color name="sushi_grey_200">#E8E8E8</color>
    <color name="sushi_grey_300">#CFCFCF</color>
    <color name="sushi_grey_400">#B5B5B5</color>
    <color name="sushi_grey_500">#9C9C9C</color>
    <!--  ... and so on ... -->
</resources>
```

If you wish to override some (or all) colors in your app, you can write like this in your own `colors.xml`

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <color name="sushi_black" tools:override="true">#2B2B2B</color>
    <color name="sushi_white" tools:override="true">#D9D9D9</color>
    <!--  ... and so on ... -->
</resources>
```

## Palette

<p align="center">
<img alt="color palette" style="width: 66%;" src="../../img/colors/palette.jpg">
</p>
