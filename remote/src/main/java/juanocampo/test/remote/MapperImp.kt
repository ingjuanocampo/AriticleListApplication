package juanocampo.test.remote

import juanocampo.test.data.entity.AddressRepo
import juanocampo.test.data.entity.CompanyRepo
import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.remote.api.entity.PostPojo
import juanocampo.test.remote.api.entity.UserPojo
import juanocampo.test.remote.mapper.Mapper

class MapperImp : Mapper {

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

    override fun map(post: PostPojo): PostRepo {
        return PostRepo(postId = post.id?: "", body = post.body?: "", title = post.title?: "", userId = post.userId?: "")
    }
}