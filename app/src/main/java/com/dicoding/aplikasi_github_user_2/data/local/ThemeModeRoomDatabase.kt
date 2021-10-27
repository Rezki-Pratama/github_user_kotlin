package com.dicoding.aplikasi_github_user_2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity
import com.dicoding.aplikasi_github_user_2.data.model.ThemeModeEntity

@Database(entities = [ThemeModeEntity::class], version = 1)
abstract class ThemeModeRoomDatabase : RoomDatabase() {
    abstract fun modeDao(): ThemeModeDao
    companion object {
        @Volatile
        private var INSTANCE: ThemeModeRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): ThemeModeRoomDatabase {
            if (INSTANCE == null) {
                synchronized(ThemeModeRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ThemeModeRoomDatabase::class.java, "mode_database")
                        .build()
                }
            }
            return INSTANCE as ThemeModeRoomDatabase
        }
    }
}