package juanocampo.test.remote

import juanocampo.test.data.entity.AddressRepo
import juanocampo.test.data.entity.CompanyRepo
import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.remote.api.entity.PhotoPojo
import juanocampo.test.remote.api.entity.PostPojo
import juanocampo.test.remote.api.entity.UserPojo
import juanocampo.test.remote.mapper.Mapper

class MapperImp : Mapper {

    override fun map(photoPojos: List<PhotoPojo>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        photoPojos.forEach {
            if (it.albumId != null && it.url!= null) {
                map[it.albumId] = it.url
            }
        }
        return map
    }

    override fun map(user: UserPojo): UserRepo {
        val address = AddressRepo(
            user.address?.street?: "",
            user.address?.suite?: "",
            user.address?.city?: "",
            user.address?.zipcode?: ""
        )
        val company = CompanyRepo(user.company?.name?: "",
            user.company?.catchPhrase?: "",
            user.company?.bs?: "")
        return UserRepo(
            id = user.id?: "",
            name = user.name?: "",
            address = address,
            company = company,
            email = user.email?: "",
            phone = user.phone?: "",
            username = user.username?: "",
            website = user.website?: "")
    }

    override fun map(post: PostPojo, urlPhoto: String?): PostRepo {
        return PostRepo(postId = post.id?: "", body = post.body?: "", title = post.title?: "", userId = post.userId?: "", urlPhoto = urlPhoto)
    }
}