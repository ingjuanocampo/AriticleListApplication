package juanocampo.test.articleapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.ui.detail.PostDetailScreen.Factory.POST_ID
import juanocampo.test.domain.entity.Post
import juanocampo.test.presentation.viewmodel.ArticleDetailViewModel
import juanocampo.test.presentation.viewmodel.factory.ArticleDetailViewModelFactory
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class PostDetailScreen: AppCompatActivity() {

    object Factory {
        const val POST_ID = "post_id"

        fun newIntent(id: String, context: Context): Intent {
            val intent = Intent(context, PostDetailScreen::class.java)
            intent.putExtra(POST_ID, id)
            return intent
        }
    }

    private var post: Post? = null
    @Inject
    lateinit var factory: ArticleDetailViewModelFactory
    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProviders.of(this, factory).get(ArticleDetailViewModel::class.java)

        val postId = intent.extras.getString(POST_ID)

        viewModel.getPostId(postId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.detail_title)

        loader.visibility = View.VISIBLE
        viewModel.postLiveData.observe(this, Observer {
            viewModel.getUserId(it.userId)
            postName.text = it.title
            description.text = it.body
            favorite.setImageResource(if (it.isFavorite) R.drawable.ic_favorite_24px else R.drawable.ic_favorite_border_24px)
            post = it
        })

        viewModel.userLiveData.observe(this, Observer {
            loader.visibility = View.GONE
            user_container.visibility = View.VISIBLE
            author.text = it.name
            web.text = it.website
            email.text = it.email
        })

        viewModel.errorLive.observe(this, Observer {
            Toast.makeText(applicationContext, "Something went wrong getting info", Toast.LENGTH_SHORT).show()
        })

        favorite.setOnClickListener {
            post?.apply {
                post?.isFavorite = !this.isFavorite
                viewModel.setAsFavorite(this.postId, isFavorite )
                favorite.setImageResource(if (isFavorite) R.drawable.ic_favorite_24px else R.drawable.ic_favorite_border_24px)

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

}
