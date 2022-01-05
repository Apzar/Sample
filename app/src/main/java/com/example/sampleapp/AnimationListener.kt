package com.example.sampleapp

interface AnimationListener {
    fun onFragmentAnimationStarted(enter: Boolean)
    fun onFragmentAnimationFinished(enter: Boolean)
}