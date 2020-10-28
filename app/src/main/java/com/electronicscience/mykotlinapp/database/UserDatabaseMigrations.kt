package com.electronicscience.mykotlinapp.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_01_02 = object: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //modify db
        //ex: database.execSQL("CREATE TABLE app_user_mapping (id INTEGER NOT NULL PRIMARY KEY)")
    }
}

val MIGRATIONS: Array<Migration> = arrayOf()

