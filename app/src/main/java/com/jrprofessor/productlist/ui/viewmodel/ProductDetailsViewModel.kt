package com.jrprofessor.productlist.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrprofessor.productlist.data.model.ProductModel
import com.jrprofessor.productlist.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface DetailsState {
    data class Success(val product: ProductModel) : DetailsState
    data class Error(val exception: Throwable) : DetailsState
    data object Loading : DetailsState
}

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val detailsState: StateFlow<DetailsState> = _detailsState.asStateFlow()

    init {
        val productId: String? = savedStateHandle["productId"]
        if (!productId.isNullOrEmpty()) {
            getProductDetails(productId)
        } else {
            _detailsState.value = DetailsState.Error(IllegalArgumentException("Product ID is missing"))
        }
    }

    fun getProductDetails(id: String) {
        viewModelScope.launch {
            _detailsState.value = DetailsState.Loading
            try {
                val productModel = repository.productDetailsById(id)
                _detailsState.value = DetailsState.Success(productModel)
            } catch (e: Exception) {
                _detailsState.value = DetailsState.Error(e)
            }
        }
    }
}
