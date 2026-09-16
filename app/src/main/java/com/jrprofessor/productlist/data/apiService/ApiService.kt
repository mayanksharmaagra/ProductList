package com.jrprofessor.productlist.data.apiService

import com.jrprofessor.productlist.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // https://fakestoreapi.com/products?limit={LIMIT}
// https://fakestoreapi.com/products/{id}
    @GET("products")
    fun productList(@Query("limit") limit: Int): Response<List<ProductModel>>

    @GET("products")
    fun productDetails(@Path("id") id: String): Response<ProductModel>
}