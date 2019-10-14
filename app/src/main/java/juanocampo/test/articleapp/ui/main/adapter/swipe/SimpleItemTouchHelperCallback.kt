package juanocampo.test.articleapp.ui.main.adapter.swipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import juanocampo.test.articleapp.ui.main.adapter.swipe.SimpleItemTouchHelperCallback.Factory.ALPHA_FULL

class SimpleItemTouchHelperCallback(private val listener: ItemTouchHelperAdapter): ItemTouchHelper.Callback() {

    object Factory {
        const val ALPHA_FULL = 1.0f
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        // Set movement flags based on the layout manager
        var dragFlags = 0
        var swipeFlags = 0
        dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (source.itemViewType != target.itemViewType) {
            return false
        }
        // Notify the adapter of the move
        listener.onItemMove(source.adapterPosition, target.adapterPosition)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        listener.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        viewHolder.itemView.alpha = ALPHA_FULL
    }

}