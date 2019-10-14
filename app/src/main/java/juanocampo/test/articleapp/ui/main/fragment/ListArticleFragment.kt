package juanocampo.test.articleapp.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.ui.main.adapter.PostAdapter
import juanocampo.test.presentation.entity.PostViewType
import juanocampo.test.presentation.viewmodel.ArticleListViewModel
import juanocampo.test.presentation.viewmodel.factory.ArticleListViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class ListArticleFragment : Fragment() {


    @Inject
    lateinit var viewmodelFactory: ArticleListViewModelFactory

    lateinit var viewModel: ArticleListViewModel

    private var adapter = PostAdapter({ onPostSelected(it) }, { onPostDelete(it) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewmodelFactory)
            .get(ArticleListViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        val isFavorite = arguments?.getBoolean(IS_FAVORITE) ?: false
        viewModel.observePostList(isFavorite).observe(this, Observer {
            adapter.addItems(it)
        })

    }

    private fun onPostDelete(postViewType: PostViewType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onPostSelected(postViewType: PostViewType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {

        private const val IS_FAVORITE = "isfavorite"

        @JvmStatic
        fun newInstance(favorite: Boolean): ListArticleFragment {
            return ListArticleFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_FAVORITE, favorite)
                }
            }
        }
    }
}