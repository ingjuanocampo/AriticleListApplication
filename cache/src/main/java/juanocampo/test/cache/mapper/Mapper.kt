package juanocampo.test.cache.mapper

import juanocampo.test.cache.database.entity.PostEntity
import juanocampo.test.data.entity.PostRepo

interface Mapper {
    fun map(post: PostRepo): PostEntity
    fun map(post: PostEntity): PostRepo

}