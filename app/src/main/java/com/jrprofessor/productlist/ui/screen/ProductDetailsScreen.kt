package com.jrprofessor.productlist.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jrprofessor.productlist.data.model.ProductModel
import com.jrprofessor.productlist.data.model.RatingModel
import com.jrprofessor.productlist.ui.viewmodel.DetailsState
import com.jrprofessor.productlist.ui.viewmodel.ProductDetailsViewModel

@Preview
@Composable
fun ProductDetailsPreview() {
    ProductDetailsContent(
        productModel = ProductModel(
            id = "1",
            title = "iPhone 9",
            price = 549.0,
            description = "An apple mobile which is nothing like apple. Features high quality screen and camera.",
            category = "smartphones",
            image = "https://i.dummyjson.com/data/products/1/",
            rating = RatingModel(rate = 4.5, count = 120)
        ),
        onBackClick = {}
    )
}

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.detailsState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        when (val currentState = state) {
            is DetailsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is DetailsState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentState.exception.localizedMessage ?: "Failed to load product details",
                        style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.error)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onBackClick() }) {
                        Text(text = "Go Back")
                    }
                }
            }
            is DetailsState.Success -> {
                ProductDetailsContent(
                    productModel = currentState.product,
                    onBackClick = onBackClick
                )
            }
        }
    }
}

@Composable
fun ProductDetailsContent(
    productModel: ProductModel,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(title = productModel.title, onBackClick = onBackClick)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp
        ) {
            AsyncImage(
                model = productModel.image,
                contentDescription = productModel.title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productModel.category.uppercase(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${productModel.price}",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
            }

            productModel.rating?.let { rating ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Rating: ⭐ ${rating.rate} (${rating.count} reviews)",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFFF9800)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Description",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = productModel.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF555555),
                    lineHeight = 22.sp
                )
            )
        }
    }
}

@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Text(
                text = "←",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
            )
        }
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            ),
            modifier = Modifier.padding(start = 8.dp),
            maxLines = 1
        )
    }
}
