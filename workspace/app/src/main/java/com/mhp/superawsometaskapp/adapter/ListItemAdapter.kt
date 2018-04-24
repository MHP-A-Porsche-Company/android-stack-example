package com.mhp.superawsometaskapp.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mhp.superawsometaskapp.AwsomeApp
import com.mhp.superawsometaskapp.model.ListItem
import com.mhp.superawsometaskapp.view.ListItemView
import com.mhp.superawsometaskapp.view.ListItemView_

/**
 * Implementation of [RecyclerView.Adapter] that supports [ListItem]s.
 * To set or update values just call [updateList] and the adapter will calculate the diff and
 * display the result.
 */
class ListItemAdapter(private val onClickListener: View.OnClickListener, private val context: Context) : RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {
    private var items: ArrayList<ListItem> = ArrayList()

    init {
        AwsomeApp.awsomeComponent?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemView_.build(context)
        view.setOnClickListener(onClickListener)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as ListItemView).listItem = items[position]
    }


    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Takes the new values, calculates the diff to existing values and display the results.
     */
    fun updateList(newItems: java.util.ArrayList<ListItem>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.items, newItems))
        this.items = newItems
        diffResult.dispatchUpdatesTo(this)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
        }
    }

    class MyDiffCallback(private var oldItems: java.util.ArrayList<ListItem> = java.util.ArrayList(), private var newItems: java.util.ArrayList<ListItem> = java.util.ArrayList()) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }

        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].title == newItems[newItemPosition].title
        }
    }
}
