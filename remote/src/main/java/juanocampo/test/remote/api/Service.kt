package juanocampo.test.remote.api

import juanocampo.test.remote.api.entity.PhotoPojo
import juanocampo.test.remote.api.entity.PostPojo
import juanocampo.test.remote.api.entity.UserPojo

class Service(private val api: PostApi) {

    fun fecthListPost(): List<PostPojo>? {
        val serviceResponse = api.getListPost().execute()
        return if (serviceResponse.isSuccessful && serviceResponse.body() != null)
            serviceResponse.body()!!
        else
            null
    }

    fun fecthListPhotos(): List<PhotoPojo>? {
        val serviceResponse = api.getListPhotos().execute()
        return if (serviceResponse.isSuccessful && serviceResponse.body() != null)
            serviceResponse.body()!!
        else
            null
    }

    fun getUserById(userId: String): UserPojo? {
        val serviceResponse = api.getUserId(userId).execute()
        return if (serviceResponse.isSuccessful && serviceResponse.body() != null)
            serviceResponse.body()!!
        else
            null
    }
}