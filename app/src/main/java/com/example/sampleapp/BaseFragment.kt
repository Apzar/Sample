package com.example.sampleapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

abstract class BaseFragment : Fragment(),FragmentAUIContract.View {
    private lateinit var textView: TextView
    private lateinit var nextButton: Button
    protected lateinit var contentView: ViewGroup
    private lateinit var buttonClickListener: ButtonClickListener
    private lateinit var fragmentInteraction: FragmentInteraction
    private var presenter = FragmentDependencyInjection.provideFragmentPresenter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonClickListener && context is FragmentInteraction) {
            this.buttonClickListener = context
            this.fragmentInteraction = context
        } else {
            throw IllegalStateException("Context must be of ButtonClickListener and FragmentInteraction")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = contentView.findViewById(R.id.textView)
        val text = "${AppConstants.FRAGMENT_TAG}${fragmentInteraction.getCurrentBackStackCount()}"
        textView.text = text
        nextButton = contentView.findViewById(R.id.next_button)

        nextButton.setOnClickListener {
            presenter.onNextButtonClicked()
        }
        presenter.attach(this)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if(nextAnim == 0) {
             super.onCreateAnimation(transit, enter, nextAnim)
        } else {

            AnimationUtils.loadAnimation(context, nextAnim).apply {
                setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        fragmentInteraction.onFragmentAnimationStarted(enter)
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        fragmentInteraction.onFragmentAnimationFinished(enter)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }
        }
    }

    override fun openNextFragment() {
        buttonClickListener.navigateToNextScreen()
    }

}