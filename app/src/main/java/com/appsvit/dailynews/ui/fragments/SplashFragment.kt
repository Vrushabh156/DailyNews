package com.appsvit.dailynews.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.appsvit.dailynews.R
import com.appsvit.dailynews.databinding.FragmentSplashBinding
import com.appsvit.dailynews.utils.Constants
import com.appsvit.dailynews.utils.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var context: Context

    @Inject
    lateinit var prefManager: PreferenceManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        val btmNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        btmNav.visibility = View.GONE

        var status = false
        lifecycleScope.launch {
            prefManager.readBooleanValue(Constants.FIRST).collect {
                status = it
            }
        }

        val scaleAnimation = ScaleAnimation(
            1f, 0f,
            1f, 0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 500L
            fillAfter = true
        }

        binding.lottieAnim.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
                super.onAnimationEnd(p0)
                binding.lottieAnim.startAnimation(scaleAnimation)

                Handler(Looper.getMainLooper()).postDelayed({
                    if (status) {
                        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                    }
                }, 1000)
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}
        })






        return binding.root
    }

}