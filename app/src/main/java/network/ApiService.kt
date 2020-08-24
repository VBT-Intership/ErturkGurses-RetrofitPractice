import models.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("places/visited")
    fun getAllPlace():Call<PlaceResponse>

}