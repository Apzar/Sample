package com.example.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), ButtonClickListener, FragmentInteraction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val initialFragment = FragmentDependencyInjection.provideFragmentA()
        supportFragmentManager.commit {
            replace(R.id.fragment_container, initialFragment, AppConstants.FRAGMENT_TAG)
            addToBackStack(AppConstants.FRAGMENT_TAG)
        }
    }

    override fun navigateToNextScreen() {
        if (getCurrentBackStackCount() %2 == 0) {
            addFragment(FragmentDependencyInjection.provideFragmentA())
        } else {
           addFragment(FragmentDependencyInjection.provideFragmentB())
        }
    }

    override fun navigateToPreviousScreen() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        if (getCurrentBackStackCount() > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    private fun addFragment(fragment: Fragment) {

        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_up,
                0,
                0,
                R.anim.slide_down
            )
            add(R.id.fragment_container, fragment, AppConstants.FRAGMENT_TAG)
            addToBackStack(AppConstants.FRAGMENT_TAG)
        }

    }

    override fun getCurrentBackStackCount(): Int {
        return supportFragmentManager.backStackEntryCount
    }

    override fun onFragmentAnimationStarted(enter: Boolean) {
        if (!enter) {
            showFragment(getCurrentFragment())
        }
    }

    override fun onFragmentAnimationFinished(enter: Boolean) {
        if (enter) {
            hideFragment(getPreviousFragment())
        }
    }

    private fun getCurrentFragment(): Fragment? {
        val index = getCurrentBackStackCount() - 1
        if(index >= 0) {
            return supportFragmentManager.fragments[index]
        }
        return null
    }

    private fun getPreviousFragment(): Fragment? {
        val index = getCurrentBackStackCount() - 2
        if(index >= 0) {
            return supportFragmentManager.fragments[index]
        }
        return null
    }

    private fun showFragment(fragment: Fragment?) {
        if (fragment != null && fragment.isHidden) {
            supportFragmentManager.commit {
                show(fragment)
            }
        }
    }

    private fun hideFragment(fragment: Fragment?) {
        if (fragment != null && !fragment.isHidden) {
            supportFragmentManager.commit {
                hide(fragment)
            }
        }
    }

}