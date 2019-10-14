package juanocampo.test.articleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.delegate.DelegateAdapter
import juanocampo.test.presentation.entity.PostViewType

class PostDelegateAdapter(
    private val onPostFavorite: (PostViewType) -> Unit,
    private val onSelected: (PostViewType) -> Unit
) : DelegateAdapter<PostDelegateAdapter.ViewHolder, PostViewType> {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(parent, onPostFavorite, onSelected)

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: PostViewType) =
        viewHolder.bind(viewType)

    class ViewHolder(
        viewGroup: ViewGroup,
        private val onPostFavorite: (PostViewType) -> Unit,
        private val onSelected: (PostViewType) -> Unit
    ) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.post_item,
                viewGroup,
                false
            )
        ) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val readDoc: View = itemView.findViewById(R.id.read_doc)
        private val starFavorite: ImageView = itemView.findViewById(R.id.star_favorite)


        fun bind(post: PostViewType) {
            title.text = post.title
            starFavorite.setImageResource(if (post.isFavorite) R.drawable.ic_favorite_24px else R.drawable.ic_favorite_border_24px)
            starFavorite.visibility = View.VISIBLE
            readDoc.visibility = if (!post.isRead) View.VISIBLE else View.GONE

            starFavorite.setOnClickListener {
                onPostFavorite(post)
            }

            itemView.setOnClickListener {
                onSelected(post)
            }
        }
    }
}