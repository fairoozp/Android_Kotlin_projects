package com.example.hilt

import android.app.Application
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()