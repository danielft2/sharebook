package com.example.sharebook.discovery_feature.presentation.state

import com.example.sharebook.discovery_feature.data.remote.model.SearchModel

data class SearchFormState (
    val query: String = "",
)

fun SearchFormState.toSearchModel(): SearchModel {
    return SearchModel(
        query = query,
    )
}