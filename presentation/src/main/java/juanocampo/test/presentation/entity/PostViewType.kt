package juanocampo.test.presentation.entity

import com.ingjuanocampo.cdapter.RecyclerViewType

class PostViewType(val postId: String,
                   val userId: String,
                   val title: String,
                   val body: String,
                   val isFavorite: Boolean,
                   val isRead: Boolean): RecyclerViewType {

    override fun getDelegateId(): Int {
        return postId.hashCode()
    }

    override fun getViewType(): Int {
        return 1
    }
}