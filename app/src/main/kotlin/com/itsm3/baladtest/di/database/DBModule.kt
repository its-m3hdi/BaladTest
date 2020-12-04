package com.itsm3.baladtest.di.database

import androidx.room.Room
import com.itsm3.baladtest.data.db.explore.ExploreDB
import com.itsm3.baladtest.di.application.MyApplication
import dagger.Module
import dagger.Provides

@Module
object DbModule {
    @Provides
    @JvmStatic
    fun provideExploreDb(context: MyApplication) =
        Room.databaseBuilder(context, ExploreDB::class.java, "explore_db").build()

    @Provides
    @JvmStatic
    fun provideExploreDao(exploreDB: ExploreDB) = exploreDB.exploreDao()
}