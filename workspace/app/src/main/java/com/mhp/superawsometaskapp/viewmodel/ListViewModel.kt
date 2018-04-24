package com.mhp.superawsometaskapp.viewmodel

import com.mhp.superawsometaskapp.model.ListItem
import io.reactivex.subjects.BehaviorSubject

interface ListViewModel {
    /**
     *The list of [ListItem]s that can be observed to keep up to date with the current state.
     */
    val items: BehaviorSubject<ArrayList<ListItem>>
}