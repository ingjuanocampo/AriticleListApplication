package juanocampo.test.articleapp.ui.main.adapter

import androidx.collection.SparseArrayCompat
import juanocampo.test.articleapp.delegate.BaseAdapter
import juanocampo.test.articleapp.delegate.appendDelegate
import juanocampo.test.presentation.entity.PostViewType

class PostAdapter(onPostSelectedSelected: (PostViewType) -> Unit, onPoastDeleted: (PostViewType) -> Unit) : BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(1)
        delegateAdapters.appendDelegate(1, PostDelegateAdapter(onPostSelectedSelected, onPoastDeleted))
    }
}