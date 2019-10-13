package juanocampo.test.domain.entity

data class Post(
    var postId: String,
    var userId: String,
    var title: String,
    var body: String,
    var isFavorite: Boolean = false,
    var isRead:Boolean = true)
