package com.jrprofessor.productlist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrprofessor.productlist.R
import com.jrprofessor.productlist.data.model.ProductModel

@Preview
@Composable
fun ProductListPreview() {

    ProductListScreen()
}

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    itemClick: () -> Unit = {}
) {
    val list = listOf<ProductModel>(
        ProductModel(
            id = "122",
            title = "iPhone 9",
            price = 549,
            description = "An apple mobile which is nothing like apple",
            category = "smartphones",
            image = "https://i.dummyjson.com/data/products/1/"
        ),
        ProductModel(
            id = "123",
            title = "iPhone 9",
            price = 549,
            description = "An apple mobile which is nothing like apple",
            category = "smartphones",
            image = "https://i.dummyjson.com/data/products/1/"
        )
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "Product List",
                style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                items(items = list, key = { it.id }) {
                    ProductListItem(productModel = it, itemClick)
                }
            }
        }
    }
}

@Composable
fun ProductListItem(productModel: ProductModel, itemClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        onClick = itemClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp)
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentDescription = "Product Item"
            )
            //product title
            Text(
                text = productModel.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6B6767)
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            //product description
            Text(
                text = productModel.description,
                style = TextStyle(
                    fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(
                        0xFFA9A7A7
                    )
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
//product category
                Text(
                    text = productModel.category,
                    style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(
                            0xFFA9A7A7
                        )
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                //product price
                Text(
                    text = productModel.price.toString(),
                    style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(
                            0xFFA9A7A7
                        )
                    )
                )
            }
        }
    }
}
