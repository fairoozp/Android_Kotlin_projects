package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager

class MainActivity2 : AppCompatActivity() {

    private lateinit var scene1: Scene
    private lateinit var scene2: Scene
    private lateinit var currentScene: Scene
    private lateinit var transition: Transition
    private lateinit var rootLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rootLayout = findViewById(R.id.rootLayout)

        scene1 = Scene.getSceneForLayout(rootLayout, R.layout.scene1, this)
        scene2 = Scene.getSceneForLayout(rootLayout, R.layout.scene2, this)

        scene1.enter()
        currentScene = scene1

        transition = TransitionInflater.from(this).inflateTransition(R.transition.my_transition)

        rootLayout.setOnClickListener {
            currentScene = if (currentScene == scene1) {
                TransitionManager.go(scene2, transition)
                scene2
            } else {
                TransitionManager.go(scene1, transition)
                scene1
            }
        }

    }
}