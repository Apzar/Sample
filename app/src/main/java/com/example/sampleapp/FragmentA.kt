package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentA: BaseFragment() {

    companion object {
        fun newInstance() = FragmentA()
    }

    val viewTag = "fragmentA"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentView = inflater.inflate(R.layout.fragment_a, container, false) as ViewGroup
        return contentView
    }
}