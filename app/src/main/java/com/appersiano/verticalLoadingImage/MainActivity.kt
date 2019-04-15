package com.appersiano.verticalLoadingImage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAnimate.setOnClickListener {
            val from = tvValueFrom.text.toString().toInt()
            val to = tvValueTo.text.toString().toInt()
            val duration = tvDuration.text.toString().toLong()
            vlImage.setProgress(from, to, duration)
        }
    }
}
