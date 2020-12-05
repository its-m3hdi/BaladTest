package com.itsm3.baladtest.data.api.dto.explore

import com.google.gson.annotations.SerializedName
import com.itsm3.baladtest.data.api.dto.explore.sub.Response

data class ExploreResponseDto(
    @SerializedName("response") val response: Response
)