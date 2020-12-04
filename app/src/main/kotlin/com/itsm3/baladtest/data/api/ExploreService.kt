package com.itsm3.baladtest.data.api

import com.itsm3.baladtest.data.api.dto.explore.ExploreResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreService {
    //@TODO provide it in dagger graph
    @GET("venues/explore")
    fun explore(
        @Query("ll") latLng: String,
        @Query("limit") limit: Int,
        @Query("radius") radius: Int,
        @Query("offset") offset: Int,
    ): Single<ExploreResponseDto>
}