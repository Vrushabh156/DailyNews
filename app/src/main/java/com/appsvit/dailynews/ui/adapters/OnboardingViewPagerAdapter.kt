package com.appsvit.dailynews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appsvit.dailynews.data.remote.OnboardingItem
import com.appsvit.dailynews.databinding.OnboardingItemBinding

class OnboardingViewPagerAdapter(
    private val context: Context,
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingViewPagerAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val binding =
            OnboardingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class OnboardingItemViewHolder(private val binding: OnboardingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardingItem: OnboardingItem) {
            binding.tvTitle.text = onboardingItem.title
            binding.tvDescription.text = onboardingItem.description
        }
    }
}