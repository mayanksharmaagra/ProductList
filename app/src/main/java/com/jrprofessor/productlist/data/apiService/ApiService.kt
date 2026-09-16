package com.jrprofessor.productlist.data.apiService

import com.jrprofessor.productlist.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun productList(@Query("limit") limit: Int = 20): Response<List<ProductModel>>

    @GET("products/{id}")
    suspend fun productDetails(@Path("id") id: String): Response<ProductModel>
}
