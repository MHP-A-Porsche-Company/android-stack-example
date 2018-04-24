package com.mhp.superawsometaskapp.viewmodel

import com.mhp.superawsometaskapp.model.ListItem
import com.mhp.superawsometaskapp.service.ListItemService
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * View model for a view that displays existing instances of [ListItem]. Uses dependency injection
 * to get an instance of [ListItemService] to handle persistence.
 */
class DefaultListViewModel @Inject constructor(listItemService: ListItemService) : ListViewModel {

    /**
     *
     *The list of [ListItem]s that can be observed to keep up to date with the current state.
     */
    override val items = BehaviorSubject.create<ArrayList<ListItem>>()!!

    init {
        // Subscribe to value changes of the persistence unit
        listItemService.items.subscribe(items::onNext)
    }
}