package com.electronicscience.mykotlinapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class UserDao {

    @Insert
    abstract fun insert(user: User): Long

    @Update
    abstract fun update(user: User)

    @Delete
    abstract fun delete(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    abstract fun getUser(username: String): User?

    @Query("SELECT * FROM users")
    abstract fun getAllUsers(): List<User>

    @Query("SELECT * FROM users")
    abstract fun getAllUsersLiveData(): LiveData<List<User>>
}