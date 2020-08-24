import models.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("places/visited")
    fun getAllPlace():Call<PlaceResponse>

}