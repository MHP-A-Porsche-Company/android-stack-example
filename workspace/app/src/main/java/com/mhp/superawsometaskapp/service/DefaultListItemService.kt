package com.mhp.superawsometaskapp.service

import com.mhp.superawsometaskapp.model.ListItem
import io.reactivex.subjects.BehaviorSubject

/**
 * A simple DAO service that can manage instances of [ListItem]. For the sake of simplicity the
 * values will not be persisted in this implementation
 */
class DefaultListItemService : ListItemService {

    override val items = BehaviorSubject.create<ArrayList<ListItem>>()!!

    override fun findById(value: String?): ListItem? {
        return items.value.find { it -> it.id == value }
    }

    override fun addListItem(item: ListItem) {
        val currentItems = items.value
        currentItems.add(item)
        items.onNext(currentItems)
    }

    override fun updatedListItem(item: ListItem) {
        val currentItems = items.value
        @Suppress("UNCHECKED_CAST") // Trust me, I'm a professional 
        val newItems = currentItems.clone() as ArrayList<ListItem>
        newItems.forEach {
            if (it.id == item.id) {
                newItems[newItems.indexOf(it)] = item
            }
        }
        items.onNext(newItems)
    }

    override fun removeListItem(itemId: String) {
        items.onNext(items.value.filter { e -> e.id != itemId } as ArrayList<ListItem>)
    }

    init {
        items.onNext(ArrayList())
    }
}