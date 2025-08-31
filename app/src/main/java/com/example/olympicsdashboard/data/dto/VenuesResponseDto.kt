package com.example.olympicsdashboard.data.dto

import com.google.gson.annotations.SerializedName


data class VenuesResponseDto (
    @SerializedName("data" ) var data : ArrayList<Venue> = arrayListOf()
)


data class Venue (
    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)