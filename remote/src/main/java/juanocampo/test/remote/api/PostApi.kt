package juanocampo.test.remote.api

import juanocampo.test.remote.api.entity.PhotoPojo
import juanocampo.test.remote.api.entity.PostPojo
import juanocampo.test.remote.api.entity.UserPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    fun getListPost(): Call<List<PostPojo>>

    @GET("photos")
    fun getListPhotos(): Call<List<PhotoPojo>>

    @GET("/users/{userId}")
    fun getUserId(@Path("userId") userId: String): Call<UserPojo>
}