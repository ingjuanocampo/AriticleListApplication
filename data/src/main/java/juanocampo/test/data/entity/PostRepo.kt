package juanocampo.test.data.entity

class PostRepo(
    var postId: String,
    var userId: String,
    var title: String,
    var body: String,
    var isFavorite: Boolean = false,
    var isRead:Boolean = true,
    var urlPhoto: String?)