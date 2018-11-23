package com.example.okta.applicationkade.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.okta.footballmatchschedule.database.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            FavoriteMatches.TABLE_FAVORITE_MATCH, true,
            FavoriteMatches.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatches.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatches.MATCH_DATE to TEXT,
            FavoriteMatches.TEAM_NAME_AWAY to TEXT,
            FavoriteMatches.TEAM_SCORE_AWAY to TEXT,
            FavoriteMatches.TEAM_NAME_HOME to TEXT,
            FavoriteMatches.TEAM_SCORE_HOME to TEXT
        )
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatches.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)