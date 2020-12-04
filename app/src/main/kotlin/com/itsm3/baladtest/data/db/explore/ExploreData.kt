package com.itsm3.baladtest.data.db.explore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itsm3.baladtest.domain.entity.VenuesEntity

sealed class ExploreData {
    @Entity(tableName = "explore_table")
    data class Explore(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "name") val name: String
    ) : ExploreData() {
        fun map() = VenuesEntity.Explore(id, name)
    }
}