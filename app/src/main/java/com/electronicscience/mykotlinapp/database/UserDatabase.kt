package com.electronicscience.mykotlinapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        // Optional replacement for getInstance
        // call using UserDatabase(Context)
        operator fun invoke(context: Context) =
            INSTANCE ?: getNewInstance(context).also { INSTANCE = it }

        fun getInstance(context: Context): UserDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = getNewInstance(context)
                INSTANCE = instance
            }
            return instance
        }

        private fun getNewInstance(context: Context): UserDatabase {
            return Room.databaseBuilder(context, UserDatabase::class.java, "userDatabase.db")
                .allowMainThreadQueries()
                .addMigrations(*MIGRATIONS)
                .build()
        }
    }
}