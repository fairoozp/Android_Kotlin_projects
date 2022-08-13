package com.example.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MyModule {

    @Provides
    fun myString(): String {
        return "Hello, Do you get the message from module"
    }
}