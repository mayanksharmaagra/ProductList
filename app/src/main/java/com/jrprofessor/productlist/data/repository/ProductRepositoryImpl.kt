package com.jrprofessor.productlist.data.repository

import com.jrprofessor.productlist.data.apiService.ApiService
import com.jrprofessor.productlist.data.model.ProductModel
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getProductList(limit: Int): List<ProductModel> {
        val response = apiService.productList(limit)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Failed to load products: ${response.message()}")
        }
    }

    override suspend fun productDetailsById(id: String): ProductModel {
        val response = apiService.productDetails(id)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Failed to load product details: ${response.message()}")
        }
    }
}
