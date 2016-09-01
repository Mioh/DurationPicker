# DurationPicker
Material Design duration picker for Android

![alt tag](https://raw.githubusercontent.com/mioh/DurationPicker/master/DurationPicker_Screenshot.png)

** How to **

1. Let an activity implement the interface `DurationPickerFragment.Listener`
2. Specify what metric should be shown with `DurationPickerFragment.setMetrics()` after instantiating the date picker object
3. Define the `onDurationPickerResult()` method to use the result
4. Rock and Roll!

** Friendly reminder **

Be aware that the duration picker does not understand what metrics are used and the result must therefore be converted manually e.g. one minute and 90 seconds equals two minutes and 30 seconds when selected by a picker utilizing hours, minutes and seconds as metrics.
