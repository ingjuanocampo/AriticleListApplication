package juanocampo.test.data.mapper

import juanocampo.test.data.entity.PostRepo
import juanocampo.test.domain.entity.Post

interface Mapper {
    fun map(post: Post): PostRepo

    fun map(post: PostRepo): Post

}