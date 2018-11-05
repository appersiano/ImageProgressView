[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ImageProgressView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7279)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
# ImageProgressView
Stop using boring standard progress bar...use images!

## Example

<img src="https://raw.githubusercontent.com/appersiano/imageprogressview/master/img/sample.gif" width="200">

### Installing

Add this to your `build.gradle` file

```gradle
implementation 'com.github.appersiano:imageprogressview:-SNAPSHOT'
```

### Usage

Inside your `layout.xml`

```xml
<com.appersiano.progressimage.ProgressImage
       android:id="@+id/vlImage"
       android:layout_width="0dp"
       app:imageShadowColor="#FF0000"
       app:srcImage="@drawable/tree"
       android:layout_height="200dp"
       android:layout_marginBottom="8dp"
       app:layout_constraintBottom_toBottomOf="parent"
       app:layout_constraintEnd_toEndOf="parent"
       app:layout_constraintStart_toStartOf="parent"
       android:layout_marginTop="8dp"
       app:layout_constraintTop_toBottomOf="@+id/btnAnimate"/>
```
and in your Activity/Fragment
```kotlin
btnAnimate.setOnClickListener {
            vlImage.setProgress(tvValue.text.toString().toInt(), true)
        }
```
## Contribute

Feel free to fork and add other functionalities! Don't forget to make a pull request!

## Author

* **Alessandro Persiano** - [appersiano](https://github.com/appersiano)
