package com.example.sharebook.home_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.home_feature.data.remote.model.toBookPreviewModel
import com.example.sharebook.home_feature.domain.adpater.HomeRepository
import com.example.sharebook.home_feature.domain.model.ListBooksModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListBooksUseCase @Inject constructor (private val homeRepository: HomeRepository) {
    operator fun invoke(): Flow<Resource<ListBooksModel>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = homeRepository.listBooks()
                emit(Resource.Success(ListBooksModel(
                    availableBooks = response.availableBooks.map { it.toBookPreviewModel() },
                    favoriteGenders = response.favoriteGenders.map { it.toBookPreviewModel() },
                    nextToYou = response.nextToYou.map { it.toBookPreviewModel() }
                )))
            } catch (e: HttpException) {
                emit(Resource.Error("Ocorreu um erro inesperado!"))
            } catch (e: IOException) {
                emit(Resource.Error("Aconteu um erro inesperado, verifique sua conexão!"))
            } finally {
                emit(Resource.Finnaly())
            }
        }
    }
}