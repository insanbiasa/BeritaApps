import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {


    companion object {
        fun getRetrofit() : BeritaService {

            val retrofit : Retrofit = Retrofit.Builder()

                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service : BeritaService = retrofit.create(BeritaService::class.java)

            return service
        }
    }
}