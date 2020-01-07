package juanocampo.test.remote

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.data.source.RemoteDataSource
import juanocampo.test.remote.api.Service
import juanocampo.test.remote.mapper.Mapper

class RemoteDataSourceImp(private val service: Service, private val mapper: Mapper) :
    RemoteDataSource {

    override fun getUserById(userId: String): UserRepo? {
        val userResponse = service.getUserById(userId)
        return if (userResponse != null) {
            mapper.map(userResponse)
        } else {
            null
        }
    }

    override fun fetchPost(): List<PostRepo>? {
        val photosMap = fetchListPhotos()
        return service.fecthListPost()?.map {
            val photoUrl = it.id ?: ""
            mapper.map(it, photosMap?.get(photoUrl)) }
    }

    private fun fetchListPhotos(): Map<String, String>? {
        val response = service.fecthListPhotos()
        return if (response != null) mapper.map(response) else null
    }

}