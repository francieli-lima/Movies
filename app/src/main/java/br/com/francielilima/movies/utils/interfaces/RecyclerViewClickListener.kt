package br.com.francielilima.movies.utils.interfaces

import br.com.francielilima.movies.utils.enums.ClickCategory

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(value: Any, category: ClickCategory) {}
    fun onRecyclerViewItemLongClicked(value: Any) {}
}