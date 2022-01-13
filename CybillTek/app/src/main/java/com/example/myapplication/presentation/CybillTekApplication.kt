package com.example.myapplication.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CybillTekApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}