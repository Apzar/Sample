package com.example.sampleapp

interface FragmentAUIContract{

    interface View {
        fun openNextFragment()
    }

    interface Presenter {
        fun attach(view: View)
        fun onNextButtonClicked()
    }
}