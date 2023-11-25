package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.event.ChooseBookEvent
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.state.ChosseBookState
import com.example.sharebook.exchanges_feature.data.local.booksInMemory
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChooseBookViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(ChosseBookState())
    private set

    fun event(chooseBookEvent: ChooseBookEvent) {
        when (chooseBookEvent) {
            is ChooseBookEvent.SearchChange -> {
                state = state.copy(
                    search = chooseBookEvent.term,
                    bookListFilter = state.bookListOriginal.filter {
                        it.name.lowercase(Locale.ROOT)
                            .contains(chooseBookEvent.term.lowercase(Locale.ROOT))
                    }
                )
            }
        }
    }

    init {
        val bookList = booksInMemory()
        state = state.copy(
            bookListOriginal = bookList,
            bookListFilter = bookList
        )
    }
}