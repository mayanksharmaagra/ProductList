package com.jrprofessor.productlist.data.repository

import com.jrprofessor.productlist.data.model.ProductModel

interface ProductRepository {
    suspend fun  getProductList(limit: Int): List<ProductModel>
    suspend fun productDetailsById(id: String): ProductModel
}