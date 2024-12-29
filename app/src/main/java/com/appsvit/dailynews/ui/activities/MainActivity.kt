package com.appsvit.dailynews.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsvit.dailynews.databinding.ActivityMainBinding
import com.appsvit.dailynews.utils.NetworkUtil
import com.appsvit.dailynews.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkUtil
            .observe(this) {
                when (it) {
                    Status.AVAILABLE -> binding.text.text = "Available"
                    Status.UNAVAILABLE -> binding.text.text = "Unavailable"
                    Status.LOSING -> binding.text.text = "Losing"
                }
            }
    }
}