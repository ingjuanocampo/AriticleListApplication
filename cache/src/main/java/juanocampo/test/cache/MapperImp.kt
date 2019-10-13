package juanocampo.test.cache

import juanocampo.test.cache.database.entity.PostEntity
import juanocampo.test.cache.mapper.Mapper
import juanocampo.test.data.entity.PostRepo

internal class MapperImp: Mapper {
    override fun map(post: PostRepo): PostEntity {
        return PostEntity(post.postId, post.userId, post.title, post.body, post.isFavorite, post.isRead)
    }

    override fun map(post: PostEntity): PostRepo {
        return PostRepo(post.postId, post.userId, post.title, post.body, post.isFavorite, post.isRead)
    }
}