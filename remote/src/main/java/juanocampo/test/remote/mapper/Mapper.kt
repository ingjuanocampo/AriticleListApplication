package juanocampo.test.remote.mapper

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.remote.api.entity.PhotoPojo
import juanocampo.test.remote.api.entity.PostPojo
import juanocampo.test.remote.api.entity.UserPojo

interface Mapper {
    fun map(user: UserPojo): UserRepo
    fun map(post: PostPojo, urlPhoto: String?): PostRepo
    fun map(photoPojos: List<PhotoPojo>): Map<String, String>
}