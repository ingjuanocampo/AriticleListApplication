package juanocampo.test.data.source

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo

interface RemoteDataSource {

    fun fetchPost(): List<PostRepo>?

    fun getUserById(userId: String): UserRepo?
}