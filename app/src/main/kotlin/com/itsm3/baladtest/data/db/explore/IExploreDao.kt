package com.itsm3.baladtest.data.db.explore

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.itsm3.baladtest.data.db.IBaseDao
import io.reactivex.Flowable

@Dao
interface IExploreDao : IBaseDao<ExploreData.Explore> {
    @Query("SELECT * FROM explore_table WHERE id = :id")
    override fun select(id: Long): Flowable<ExploreData.Explore>

    @Query("SELECT * FROM explore_table ORDER BY id ASC")
    override fun selectAllPages(): DataSource.Factory<Int, ExploreData.Explore>

    @Query("DELETE FROM explore_table")
    override fun truncate()

    @Query("UPDATE sqlite_sequence SET seq = 0")
    fun resetId()
}