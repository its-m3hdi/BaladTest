package com.itsm3.baladtest.data.api.dto.explore.sub

import com.google.gson.annotations.SerializedName

data class Icon (

    @SerializedName("prefix") val prefix : String,
    @SerializedName("suffix") val suffix : String
)