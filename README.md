# ImageProgressView
Stop using boring standard progess bar...use images!

## Example

<img src="https://raw.githubusercontent.com/appersiano/imageprogressview/master/img/sample.gif" width="200">

### Installing

Add this to your gradle file

```
implementation 'com.github.appersiano:imageprogressview:-SNAPSHOT'
```

### Usage

xml 

```
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
```
btnAnimate.setOnClickListener {
            vlImage.setProgress(tvValue.text.toString().toInt(), true)
        }
```

## Author

* **Alessandro Persiano** - [appersiano](https://github.com/appersiano)
