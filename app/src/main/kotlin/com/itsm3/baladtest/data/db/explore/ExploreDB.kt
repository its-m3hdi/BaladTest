package com.itsm3.baladtest.data.db.explore

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExploreData.Explore::class], version = 1, exportSchema = false)
abstract class ExploreDB : RoomDatabase() {
    abstract fun exploreDao(): IExploreDao
}