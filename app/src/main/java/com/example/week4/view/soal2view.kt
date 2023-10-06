package com.example.week4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.data.dummy_data
import com.example.week4.R
import com.example.week4.model.categories
import com.example.week4.model.products
import com.example.week4.ui.theme.Week4Theme

@Composable
fun ListTokpedView(categoriesList: List<categories>, productList: List<products>) {
    Column {
        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tokopedia",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the Image to the right

                Image(
                    painter = painterResource(id = R.drawable.turtle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }


        Image(
            painter = painterResource(id = R.drawable.turtle),
            contentDescription = null,
            Modifier.fillMaxWidth()
                .padding(8.dp)
        )

        Text(
            text = "Categories",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(categoriesList) { categories ->
                CategoryCard(categories)
            }
        }
        Text(
            text = "Products",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(productList) {
                ProductCard(
                    it,
                    Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CategoryCard(categories: categories, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = categories.image_path),
                    contentDescription = "Product image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .width(100.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = categories.category_name,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${categories.number_of_items} Products",
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: products, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = product.image_path),
                    contentDescription = "Product image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .width(100.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = product.product_name,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${product.price}",
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = product.location,
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${product.sold} Sold",
                    modifier = Modifier.padding(4.dp),
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Week4Theme {
        ListTokpedView(
            categoriesList = dummy_data().get_data_tokopedia_category(),
            productList = dummy_data().get_data_tokopedia_product()
        )
    }
}