package juanocampo.test.data

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.data.mapper.Mapper
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User

class MapperImp: Mapper {

    override fun map(post: PostRepo): Post {
        return Post(post.postId, post.userId, post.title, post.body, post.isFavorite, post.isRead, post.urlPhoto?: "")
    }

    override fun map(userRepo: UserRepo): User {
        return User(id = userRepo.id,
            name = userRepo.name,
            username = userRepo.username,
            email = userRepo.email,
            website = userRepo.website,
            phone = userRepo.phone,
            company = "Company: ${userRepo.company.name}",
            address = "Address: ${userRepo.address.city}, ${userRepo.address.street}. Code: ${userRepo.address.zipcode}")
    }

}


