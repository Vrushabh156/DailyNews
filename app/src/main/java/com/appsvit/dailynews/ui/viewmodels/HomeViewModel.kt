package com.appsvit.dailynews.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsvit.dailynews.data.models.Article
import com.appsvit.dailynews.data.models.NewsItem
import com.appsvit.dailynews.data.repository.NewsRepository
import com.appsvit.dailynews.utils.Constants
import com.appsvit.dailynews.utils.PreferenceManager
import com.appsvit.dailynews.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _topHeadlinesState = MutableLiveData<Response<NewsItem?>>(Response.None)
    val topHeadlinesState: LiveData<Response<NewsItem?>> get() = _topHeadlinesState

    private val _topHeadlinesByCategoryState = MutableLiveData<Response<NewsItem?>>(Response.None)
    val topHeadlinesByCategoryState: LiveData<Response<NewsItem?>> get() = _topHeadlinesByCategoryState

    private val _articlesFromDb = MutableLiveData<Response<List<Article>>>(Response.None)
    val articlesFromDb: LiveData<Response<List<Article>>> get() = _articlesFromDb


    fun userOnboarded() {
        viewModelScope.launch {
            preferenceManager.saveBooleanValue(Constants.FIRST, true)
        }
    }

    fun getHeadlines() {
        viewModelScope.launch {
            newsRepository.getTopHeadlines().collect {
                _topHeadlinesState.value = it
            }
        }
    }

    fun getHeadlinesByCategory(category: String) {
        viewModelScope.launch {
            newsRepository.getHeadlinesByCategory(category)
                .collect {
                    _topHeadlinesByCategoryState.value = it
                }
        }
    }

    fun fetchHeadlinesFromDb() {
        viewModelScope.launch {
            newsRepository.fetchDataFromDb().collect {
                _articlesFromDb.value = it
            }
        }

    }
}