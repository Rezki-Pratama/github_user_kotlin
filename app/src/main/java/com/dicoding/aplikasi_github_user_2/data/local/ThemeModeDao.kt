package com.dicoding.aplikasi_github_user_2.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.model.ThemeModeEntity

@Dao
interface ThemeModeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: ThemeModeEntity)
    @Update
    fun update(user: ThemeModeEntity)
    @Delete
    fun delete(user: ThemeModeEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM themeModeEntity WHERE id=:id)")
    fun isFavorite(id: Int): LiveData<Int>
}