package com.example.sampleapp

interface FragmentInteraction: AnimationListener {
    fun getCurrentBackStackCount(): Int
}