package com.dicoding.aplikasi_github_user_2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.aplikasi_github_user_2.data.model.GithubUserEntity

@Database(entities = [GithubUserEntity::class], version = 1)
abstract class GithubUserRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): GithubUserDao
    companion object {
        @Volatile
        private var INSTANCE: GithubUserRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): GithubUserRoomDatabase {
            if (INSTANCE == null) {
                synchronized(GithubUserRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        GithubUserRoomDatabase::class.java, "user_database")
                        .build()
                }
            }
            return INSTANCE as GithubUserRoomDatabase
        }
    }
}