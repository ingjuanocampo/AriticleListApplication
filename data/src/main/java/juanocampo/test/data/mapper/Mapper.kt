package juanocampo.test.data.mapper

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.entity.UserRepo
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User

interface Mapper {
    fun map(post: PostRepo): Post
    fun map(userRepo: UserRepo): User

}