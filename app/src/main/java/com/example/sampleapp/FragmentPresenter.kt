package com.example.sampleapp

class  FragmentPresenter : FragmentAUIContract.Presenter {

    private lateinit var view: FragmentAUIContract.View

    override fun attach(view: FragmentAUIContract.View) {
        this.view = view
    }

    override fun onNextButtonClicked() {
        view.openNextFragment()
    }
}