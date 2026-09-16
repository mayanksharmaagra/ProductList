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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jrprofessor.productlist.data.model.ProductModel
import com.jrprofessor.productlist.ui.viewmodel.ProductViewModel
import com.jrprofessor.productlist.ui.viewmodel.State

@Preview
@Composable
fun ProductListPreview() {
    ProductListContent(
        products = listOf(
            ProductModel(
                id = "1",
                title = "iPhone 9",
                price = 549.0,
                description = "An apple mobile which is nothing like apple",
                category = "smartphones",
                image = "https://i.dummyjson.com/data/products/1/"
            )
        ),
        itemClick = {}
    )
}

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
    itemClick: (String) -> Unit = {}
) {
    val state by viewModel.product.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        when (val currentState = state) {
            is State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is State.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentState.exception.localizedMessage ?: "An error occurred",
                        style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.error)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getProductList() }) {
                        Text(text = "Retry")
                    }
                }
            }
            is State.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Product List",
                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProductListContent(products = currentState.product, itemClick = itemClick)
                }
            }
        }
    }
}

@Composable
fun ProductListContent(
    products: List<ProductModel>,
    itemClick: (String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = products, key = { it.id }) { product ->
            ProductListItem(productModel = product, itemClick = { itemClick(product.id) })
        }
    }
}

@Composable
fun ProductListItem(
    productModel: ProductModel,
    itemClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        onClick = itemClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            AsyncImage(
                model = productModel.image,
                contentDescription = productModel.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productModel.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = productModel.description,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFF666666)
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = productModel.category.uppercase(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${productModel.price}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
            }
        }
    }
}
