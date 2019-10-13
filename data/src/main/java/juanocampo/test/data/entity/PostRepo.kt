package juanocampo.test.data.entity


class PostRepo(
    var postId: String? = null,
    var userId: String? = null,
    var title: String? = null,
    var body: String? = null,
    var isFavorite: Boolean = false,
    var isRead:Boolean = true)