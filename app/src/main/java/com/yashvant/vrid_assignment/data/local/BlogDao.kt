package com.yashvant.vrid_assignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yashvant.vrid_assignment.data.model.BlogPost
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {
    @Query("SELECT * FROM blog_posts ORDER BY date DESC")
    fun getAllBlogs(): Flow<List<BlogPost>>

    @Query("SELECT * FROM blog_posts WHERE id = :blogId")
    suspend fun getBlogById(blogId: Int): BlogPost?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogs(blogs: List<BlogPost>)

    @Query("DELETE FROM blog_posts")
    suspend fun deleteAllBlogs()

    @Query("SELECT COUNT(*) FROM blog_posts")
    suspend fun getBlogCount(): Int
}

