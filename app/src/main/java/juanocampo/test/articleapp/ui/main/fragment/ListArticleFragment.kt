package juanocampo.test.articleapp.ui.main.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.ui.detail.PostDetailScreen
import juanocampo.test.articleapp.ui.main.adapter.PostAdapter
import juanocampo.test.articleapp.ui.main.adapter.swipe.ItemTouchHelperAdapter
import juanocampo.test.articleapp.ui.main.adapter.swipe.SimpleItemTouchHelperCallback
import juanocampo.test.presentation.entity.PostViewType
import juanocampo.test.presentation.viewmodel.ArticleListViewModel
import juanocampo.test.presentation.viewmodel.factory.ArticleListViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class ListArticleFragment : Fragment(), ItemTouchHelperAdapter {

    @Inject
    lateinit var viewmodelFactory: ArticleListViewModelFactory

    lateinit var viewModel: ArticleListViewModel

    private var adapter = PostAdapter({ onFavorite(it) }, { onSelected(it) })

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

        sort.setOnClickListener {
            recyclerView.scrollToPosition(0)
            viewModel.sort()
        }

        val swipeAnimation = SimpleItemTouchHelperCallback(this)
        val mItemTouchHelper = ItemTouchHelper(swipeAnimation)
        mItemTouchHelper.attachToRecyclerView(recyclerView)

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return false
    }

    override fun onItemDismiss(position: Int) {
        val post = adapter.items.get(position)
        if (post is PostViewType) {
            viewModel.deleteById(post.postId)
        }
    }

    private fun onSelected(postViewType: PostViewType) {
        startActivityForResult(
            PostDetailScreen.Factory.newIntent(postViewType.postId, context!!),
            0
        )
    }

    private fun onFavorite(postViewType: PostViewType) {
        viewModel.setAsFavorite(postViewType.postId, !postViewType.isFavorite)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setOnQueryTextFocusChangeListener(object : SearchView.OnQueryTextListener,
                View.OnFocusChangeListener {
                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.search(it)
                    }
                    return false
                }
            })
        }

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