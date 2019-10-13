package juanocampo.test.remote.api.di

import dagger.Module
import dagger.Provides
import juanocampo.test.remote.api.PostApi
import juanocampo.test.remote.api.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApiModule {

    @Named("BaseApiURL")
    @Provides
    fun providesBaseApiUrl(): String = "https://jsonplaceholder.typicode.com"

    @Provides
    fun providesApi(@Named("BaseApiURL") baseURL: String): PostApi {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    fun providesService(api: PostApi) = Service(api)


}