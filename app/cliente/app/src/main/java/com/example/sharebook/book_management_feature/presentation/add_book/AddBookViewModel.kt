package com.example.sharebook.book_management_feature.presentation.add_book

import androidx.lifecycle.ViewModel
import com.example.sharebook.book_management_feature.domain.usecase.AddBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,

) : ViewModel(){

}