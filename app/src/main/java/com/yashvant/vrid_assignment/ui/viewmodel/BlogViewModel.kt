package com.yashvant.vrid_assignment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yashvant.vrid_assignment.data.local.BlogDatabase
import com.yashvant.vrid_assignment.data.model.BlogPost
import com.yashvant.vrid_assignment.data.remote.RetrofitInstance
import com.yashvant.vrid_assignment.data.repository.BlogRepository
import com.yashvant.vrid_assignment.data.repository.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BlogListState(
    val blogs: List<BlogPost> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoadingMore: Boolean = false,
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true
)

class BlogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BlogRepository

    private val _blogListState = MutableStateFlow(BlogListState())
    val blogListState: StateFlow<BlogListState> = _blogListState.asStateFlow()

    init {
        val database = BlogDatabase.getDatabase(application)
        repository = BlogRepository(RetrofitInstance.api, database.blogDao())
        loadBlogs()
    }

    fun loadBlogs(refresh: Boolean = false) {
        viewModelScope.launch {
            if (refresh) {
                _blogListState.value = _blogListState.value.copy(
                    currentPage = 1,
                    blogs = emptyList(),
                    hasMorePages = true
                )
            }

            repository.getBlogs(page = _blogListState.value.currentPage, forceRefresh = refresh)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _blogListState.value = _blogListState.value.copy(
                                isLoading = _blogListState.value.currentPage == 1,
                                isLoadingMore = _blogListState.value.currentPage > 1
                            )
                        }
                        is Resource.Success -> {
                            val newBlogs = resource.data
                            _blogListState.value = _blogListState.value.copy(
                                blogs = if (_blogListState.value.currentPage == 1) {
                                    newBlogs
                                } else {
                                    _blogListState.value.blogs + newBlogs
                                },
                                isLoading = false,
                                isLoadingMore = false,
                                error = null,
                                hasMorePages = newBlogs.isNotEmpty()
                            )
                        }
                        is Resource.Error -> {
                            _blogListState.value = _blogListState.value.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                error = resource.message
                            )
                        }
                    }
                }
        }
    }

    fun loadMoreBlogs() {
        if (!_blogListState.value.isLoadingMore && _blogListState.value.hasMorePages) {
            _blogListState.value = _blogListState.value.copy(
                currentPage = _blogListState.value.currentPage + 1
            )
            loadBlogs()
        }
    }

    fun clearError() {
        _blogListState.value = _blogListState.value.copy(error = null)
    }
}

