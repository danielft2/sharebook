package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.event

sealed class ChooseBookEvent(val term: String){
    class SearchChange(term: String): ChooseBookEvent(term)
}
