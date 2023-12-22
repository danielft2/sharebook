package com.example.sharebook.maps_feature.domain.usecases

import com.example.sharebook.core.utils.Resource
import com.example.sharebook.home_feature.data.remote.model.toBookMarker
import com.example.sharebook.home_feature.domain.adpater.HomeRepository
import com.example.sharebook.maps_feature.domain.model.BookMarker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListBooksForMapsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    operator fun invoke(): Flow<Resource<List<BookMarker>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = homeRepository.listBooks()
                emit(Resource.Success(response.nextToYou.map { it.toBookMarker() }))
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