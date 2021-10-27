package com.dicoding.aplikasi_github_user_2.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: GithubUserEntity)
    @Update
    fun update(user: GithubUserEntity)
    @Delete
    fun delete(user: GithubUserEntity)

    @Query("SELECT * from githubUserEntity ORDER BY id ASC")
    fun getAllFavorites(): LiveData<List<GithubUserEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM githubUserEntity WHERE id=:id)")
    fun isFavorite(id: Int): LiveData<Int>
}