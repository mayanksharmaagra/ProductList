package com.jrprofessor.productlist.data.repository

import com.jrprofessor.productlist.data.apiService.ApiService
import com.jrprofessor.productlist.data.model.ProductModel
import kotlin.collections.emptyList

class ProductRepositoryImpl(val apiService: ApiService) : ProductRepository {
    override suspend fun getProductList(limit: Int): List<ProductModel> {
        val response = apiService.productList(limit)
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            emptyList()
        }
    }

    override suspend fun productDetailsById(id: String): ProductModel {
        val response = apiService.productDetails(id)
//        return if (response.isSuccessful) {
//            response.body()
//        } else {
//            emptyList()
//        }
        return ProductModel()
    }

}