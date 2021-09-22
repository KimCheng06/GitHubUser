package com.example.gituser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gituser.R
import com.example.gituser.coordinator.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator by inject<Navigator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.activity = this
    }
}