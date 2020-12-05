package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("groups") val groups: List<Groups>
)