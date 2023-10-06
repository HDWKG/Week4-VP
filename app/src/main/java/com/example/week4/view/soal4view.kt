package com.example.week4.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week4.data.DataSource
import com.example.week4.model.Explore
import com.example.week4.ui.theme.Week4Theme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.week4.R

@Composable
fun ListExploreView(explore: List<Explore>) {
    Column {
        BoxWithConstraints {
            val bottomNavHeight = 45.dp
            val gridHeight = maxHeight - bottomNavHeight
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .background(Color.Black)
                    .height(gridHeight)
            ) {
                item(
                    span = { GridItemSpan(3) }
                ) {
                    SearchBar()
                }

                items(explore) {
                    ExploreCard(it)
                }
            }
        }
        BottomNavigationBarWithIcons()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        textColor = Color.White
    )
    val customBorderShape = RoundedCornerShape(32.dp)
    var src by remember { mutableStateOf("") }
    OutlinedTextField(
        value = src,
        label = { Text(text = "Search") },
        onValueChange = {
            src = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
        colors = customTextFieldColors,
        shape = customBorderShape
    )
}

@Composable
fun ExploreCard(explore: Explore) {
    Image(
        painter = painterResource(id = explore.pic_path),
        contentDescription = "Product image",
        modifier = Modifier
            .height(120.dp)
            .width(100.dp),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun BottomNavigationBarWithIcons() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = {
            Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
        }) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )

        }

        IconButton(onClick = {
            Toast.makeText(context, "Explore", Toast.LENGTH_SHORT).show()
        }) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }

        IconButton(onClick = {
            Toast.makeText(context, "Uploaded Image", Toast.LENGTH_SHORT).show()
        }) {
            Image(
                painter = painterResource(id = R.drawable.post),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }

        IconButton(onClick = {
            Toast.makeText(context, "Reels", Toast.LENGTH_SHORT).show()
        }) {
            Image(
                painter = painterResource(id = R.drawable.reels),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }

        IconButton(onClick = {
            Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
        }) {
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview() {
    Week4Theme {
        ListExploreView(explore = DataSource().loadExplore())
    }
}