package juanocampo.test.articleapp.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import juanocampo.test.presentation.entity.RecyclerViewType

interface DelegateAdapter<VH : RecyclerView.ViewHolder, VT : RecyclerViewType> {

    fun onCreateViewHolder(parent: ViewGroup): VH

    fun onBindViewHolder(viewHolder: VH, viewType: VT)
}