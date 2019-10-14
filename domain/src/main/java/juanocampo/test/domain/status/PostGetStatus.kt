package juanocampo.test.domain.status

import juanocampo.test.domain.entity.Post

sealed class PostGetStatus
data class PostSuccess(val post: Post): PostGetStatus()
object PostError: PostGetStatus()