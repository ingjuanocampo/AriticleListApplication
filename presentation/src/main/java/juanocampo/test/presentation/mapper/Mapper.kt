package juanocampo.test.presentation.mapper

import juanocampo.test.domain.entity.Post
import juanocampo.test.presentation.entity.PostViewType

interface Mapper {

    fun map(post: Post): PostViewType
}