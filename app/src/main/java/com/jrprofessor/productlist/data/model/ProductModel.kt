package com.jrprofessor.productlist.data.model

data class RatingModel(
    var rate: Double = 0.0,
    var count: Int = 0
)

data class ProductModel(
    var id: String = "",
    var title: String = "",
    var price: Double = 0.0,
    var description: String = "",
    var category: String = "",
    var image: String = "",
    var rating: RatingModel? = null
)
