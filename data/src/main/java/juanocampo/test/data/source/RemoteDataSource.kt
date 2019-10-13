package juanocampo.test.data.source

import juanocampo.test.data.entity.PostRepo

interface RemoteDataSource {

    fun fetchPost(): List<PostRepo>?

    //fun getUserById(userId: String): User?
}