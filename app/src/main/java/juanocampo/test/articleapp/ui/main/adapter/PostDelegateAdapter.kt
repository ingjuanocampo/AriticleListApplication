package juanocampo.test.articleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ingjuanocampo.cdapter.DelegateViewHolder
import com.ingjuanocampo.cdapter.RecyclerViewType
import juanocampo.test.articleapp.R
import juanocampo.test.presentation.entity.PostViewType

class PostDelegateAdapter(
    viewGroup: ViewGroup,
    private val onPostFavorite: (PostViewType) -> Unit,
    private val onSelected: (PostViewType) -> Unit
) : DelegateViewHolder(LayoutInflater.from(viewGroup.context).inflate(
    R.layout.post_item,
    viewGroup,
    false
)) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val readDoc: View = itemView.findViewById(R.id.read_doc)
    private val starFavorite: ImageView = itemView.findViewById(R.id.star_favorite)

    override fun onBindViewHolder(post: RecyclerViewType) {
        post as PostViewType
        title.text = post.title
        starFavorite.setImageResource(if (post.isFavorite) R.drawable.ic_favorite_24px else R.drawable.ic_favorite_border_24px)
        starFavorite.visibility = View.VISIBLE
        readDoc.visibility = if (!post.isRead) View.VISIBLE else View.GONE

        starFavorite.setOnClickListener {
            onPostFavorite(post)
        }

        itemView.setOnClickListener {
            onSelected(post)
        }    }
}