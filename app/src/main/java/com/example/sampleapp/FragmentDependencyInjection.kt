package com.example.sampleapp

object FragmentDependencyInjection {

    fun provideFragmentA(): FragmentA {
        return FragmentA.newInstance()
    }

    fun provideFragmentB(): FragmentB {
        return FragmentB.newInstance()
    }

    fun provideFragmentPresenter():FragmentAUIContract.Presenter {
        return FragmentPresenter()
    }
}