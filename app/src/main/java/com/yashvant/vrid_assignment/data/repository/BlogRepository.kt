package com.yashvant.vrid_assignment.data.repository

import com.yashvant.vrid_assignment.data.local.BlogDao
import com.yashvant.vrid_assignment.data.model.BlogPost
import com.yashvant.vrid_assignment.data.remote.BlogApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()
    class Loading<T> : Resource<T>()
}

class BlogRepository(
    private val apiService: BlogApiService,
    private val blogDao: BlogDao
) {

    fun getBlogs(page: Int, forceRefresh: Boolean = false): Flow<Resource<List<BlogPost>>> = flow {
        emit(Resource.Loading())

        // First emit cached data if available
        val cachedBlogs = blogDao.getAllBlogs()

        try {
            // Fetch from network
            val networkBlogs = apiService.getBlogPosts(perPage = 10, page = page)

            // Cache the data
            if (page == 1) {
                blogDao.deleteAllBlogs()
            }
            blogDao.insertBlogs(networkBlogs)

            emit(Resource.Success(networkBlogs))
        } catch (e: Exception) {
            // If network fails, try to use cached data
            val count = blogDao.getBlogCount()
            if (count > 0) {
                emit(Resource.Error("Network error. Showing cached data.", null))
                cachedBlogs.collect { blogs ->
                    emit(Resource.Success(blogs))
                }
            } else {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }

    suspend fun getBlogById(id: Int): BlogPost? {
        return blogDao.getBlogById(id)
    }

    fun getCachedBlogs(): Flow<List<BlogPost>> {
        return blogDao.getAllBlogs()
    }
}

