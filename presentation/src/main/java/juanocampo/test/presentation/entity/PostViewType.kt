package juanocampo.test.presentation.entity

class PostViewType(val postId: String,
                   val userId: String,
                   val title: String,
                   val body: String,
                   val isFavorite: Boolean,
                   val isRead: Boolean,
                   val photoUrl: String): RecyclerViewType {

    override fun getDelegateId(): Int {
        return postId.hashCode()
    }

    override fun getViewType(): Int {
        return 1
    }
}