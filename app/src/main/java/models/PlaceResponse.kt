package models


import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("places")
    val places: List<Place>
)