package juanocampo.test.presentation.viewmodel

import juanocampo.test.domain.entity.Post
import juanocampo.test.presentation.entity.PostViewType
import juanocampo.test.presentation.mapper.Mapper

class MapperImp: Mapper {
    override fun map(post: Post): PostViewType {
        return PostViewType(post.postId, post.userId, post.title, post.body, post.isFavorite, post.isRead, post.photoUrl)
    }
}