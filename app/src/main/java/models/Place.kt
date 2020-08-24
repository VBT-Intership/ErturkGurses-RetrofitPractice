package models


import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("adress")
    val adress: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("isOutdoor")
    val isOutdoor: Boolean,
    @SerializedName("lastVisitDate")
    val lastVisitDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("numberOfVisit")
    val numberOfVisit: Int,
    @SerializedName("__v")
    val v: Int
)