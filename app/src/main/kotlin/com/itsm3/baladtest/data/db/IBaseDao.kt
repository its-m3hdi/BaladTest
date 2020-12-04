package com.itsm3.baladtest.data.db

import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Flowable

interface IBaseDao <T> {
    fun select(id: Long): Flowable<T>
    fun selectAllPages(): DataSource.Factory<Int, T>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(t:T):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(t:List<T>)

    fun truncate()
}