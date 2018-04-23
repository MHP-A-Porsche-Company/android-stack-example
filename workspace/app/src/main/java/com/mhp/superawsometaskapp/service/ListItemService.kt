package com.mhp.superawsometaskapp.service

import com.mhp.superawsometaskapp.model.ListItem
import io.reactivex.subjects.BehaviorSubject

/**
 * A simple DAO service that can manage instances of [ListItem].
 */
interface ListItemService {

    /**
     *The list of [ListItem]s that can be observed to keep up to date with the current state.
     */
    val items: BehaviorSubject<ArrayList<ListItem>>

    /**
     * Persist a new instance of [ListItem].
     */
    fun addListItem(item: ListItem)

    /**
     * Persist an existing instance of [ListItem].
     */
    fun updatedListItem(item: ListItem)

    /**
     * Delete an existing instance of [ListItem] by its [ListItem.id]
     */
    fun removeListItem(itemId: String)

    /**
     * Find an existing instance of [ListItem] by its [ListItem.id]
     */
    fun findById(id: String?): ListItem?
}