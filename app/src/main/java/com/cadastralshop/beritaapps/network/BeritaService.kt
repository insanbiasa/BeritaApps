import com.cadastralshop.beritaapps.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface BeritaService {



    @GET("everything?q=movies&sortBy=publishedAt&apiKey=609fc0d3b0914b04af13e1be6e1ed0d2")
    fun  getDataBerita():Call<ResponseServer>

}