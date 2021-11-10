import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        val BASE_URL: String = "https://jsonplaceholder.typicode.com/"

        fun getApiClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}




//    private val retrofit : Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build().create(ApiService::class.java)
//    }

//    interface apiService : ApiService by lazy{
//        retrofit.create(ApiService::class.java)
//    }
}