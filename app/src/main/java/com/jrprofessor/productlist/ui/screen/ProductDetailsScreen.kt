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
fun ProductDetailsPreview() {

    ProductDetailsScreen()
}

@Composable
fun ProductDetailsScreen(
) {
    val productModel = ProductModel(
        id = "122",
        title = "iPhone 9",
        price = 549,
        description = "An apple mobile which is nothing like apple",
        category = "smartphones",
        image = "https://i.dummyjson.com/data/products/1/"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(productModel.title)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(15.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 4.dp,
            onClick = {

            }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentDescription = "Product Item"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp)
        )
        {
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

@Composable
fun TopBar(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6B6767)
                )
            )
        }
    }
}
