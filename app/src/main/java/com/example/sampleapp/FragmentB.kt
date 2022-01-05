package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FragmentB: BaseFragment() {

    companion object {
        fun newInstance() = FragmentB()
    }

    val viewTag = "fragmentB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentView = inflater.inflate(R.layout.fragment_b, container, false) as ViewGroup
        return contentView
    }
}