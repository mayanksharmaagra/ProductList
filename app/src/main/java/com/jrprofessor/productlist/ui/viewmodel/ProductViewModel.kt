package com.jrprofessor.productlist.ui.viewmodel

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

sealed interface State {
    data class Success(val product: List<ProductModel>) : State
    data class Error(val exception: Throwable) : State
    data object Loading : State
}

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<State>(State.Loading)
    val product: StateFlow<State> = _product.asStateFlow()

    init {
        getProductList()
    }

    fun getProductList(limit: Int = 20) {
        viewModelScope.launch {
            _product.value = State.Loading
            try {
                val list = repository.getProductList(limit)
                _product.value = State.Success(list)
            } catch (e: Exception) {
                _product.value = State.Error(e)
            }
        }
    }
}
