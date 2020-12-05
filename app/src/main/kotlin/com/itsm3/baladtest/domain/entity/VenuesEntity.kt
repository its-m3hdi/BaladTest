package com.itsm3.baladtest.domain.entity

import com.itsm3.baladtest.data.db.explore.ExploreData

sealed class VenuesEntity {
    data class Explore(
        val id: Int?,
        val name: String
    ) : VenuesEntity() {
        fun map() = ExploreData.Explore(id, name)
    }

}