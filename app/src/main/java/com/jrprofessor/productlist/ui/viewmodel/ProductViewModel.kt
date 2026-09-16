package com.jrprofessor.productlist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrprofessor.productlist.data.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed interface State{
    data class Success(val product: List<ProductModel>): State
    data class Error(val exception: Throwable): State
    object Loading: State
}

class ProductViewModel: ViewModel() {
    private val _product= MutableStateFlow(State.Loading)
    val product: StateFlow<State> = _product

    fun getProductList(){
        viewModelScope.launch {

        }
    }
}