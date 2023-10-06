package com.example.week4.view

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.R
import com.example.week4.data.DataSource
import com.example.week4.model.Explore
import com.example.week4.model.Feed
import com.example.week4.model.Story
import com.example.week4.model.Suggestion
import com.example.week4.model.line_chat
import com.example.week4.ui.theme.Week4Theme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun ListFeedView(
    feedList: List<Feed>,
    storyList: List<Story>,
    suggestionList: List<Suggestion>,
    explore: List<Explore>
) {
    Column {
        BoxWithConstraints {
            val bottomNavHeight = 45.dp
            val gridHeight = maxHeight - bottomNavHeight
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .background(Color.Black)
                    .height(gridHeight)
            ) {
                item(
                    span = { GridItemSpan(1) }
                ) {
                    TopBar()
                }
                item(
                    span = { GridItemSpan(1) }
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val context = LocalContext.current
                                Box(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(80.dp)
                                        .clip(CircleShape)
                                        .background(Color.Gray)
                                        .clickable {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "Add A Story",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .align(Alignment.Center)
                                    )
                                }

                                Text(
                                    text = "Your Story",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp)
                                )
                            }
                        }

                        items(storyList) {
                            StoryItem(it)
                        }
                    }
                }
                items(
                    feedList
                ) {
                    InstagramPost(it)
                }
            }
        }
        BottomNavigationBarWithIcons()
    }
}

@Composable
fun InstagramPost(feed: Feed) { // change
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // User details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = feed.pp),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = feed.name, fontWeight = FontWeight.Bold, color = Color.White)

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        // Post image
        Image(
            painter = painterResource(id = feed.contentPicture),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        val x: Boolean = feed.liked
        // Like, Comment, Share buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (x == true) { // change
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.messanger),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            val y = feed.saved

            if (y == true) {
                Image(
                    painter = painterResource(id = R.drawable.saved_light),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        // Likes
        if (x == true) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("You")// change
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append(" and ")
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("${feed.likes - 1} others") // change
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append(" liked this post")
                    }

                },
                modifier = Modifier.padding(8.dp)
            )
        } else {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("${feed.likes} Likes")
                    }
                }, modifier = Modifier.padding(8.dp)
            )
        }


        // Caption
        ExpandableText(name = feed.name, text = feed.caption)

        // Date
        val z = stringToDate(feed.date, "yyyy-MM-dd")

        val year = if (z != null) getYearFromDate(z) else null

        val textToDisplay = when {
            year == 2023 -> {
                val monthAndDate = getMonthAndDateFromDate(z!!)
                "${getMonthName(monthAndDate?.first)} ${monthAndDate?.second}"
            }

            year != null -> {
                val monthAndDate = getMonthAndDateFromDate(z!!)
                "${getMonthName(monthAndDate?.first)} ${monthAndDate?.second}, $year"
            }
            else -> "Invalid Date"
        }

        Text(
            text = textToDisplay,
            color = Color.White,
            modifier = Modifier.padding(8.dp),
            fontSize = 10.sp
        )


    }
}

fun getMonthName(month: Int?): String {
    return when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid Month"
    }
}

fun stringToDate(dateString: String, pattern: String): Date? {
    try {
        val dateFormat = SimpleDateFormat(pattern)
        return dateFormat.parse(dateString)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun getYearFromDate(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR)
}

fun getMonthAndDateFromDate(date: Date): Pair<Int, Int>? {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based in Calendar
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return Pair(month, day)
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_dark),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.width(150.dp))
            Image(
                painter = painterResource(id = R.drawable.like),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.dm),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun StoryItem(story: Story) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .clickable {
                    Toast
                        .makeText(context, "${story.name}'s Story", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Image(
                painter = painterResource(id = story.profilePic),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )

        }

        Text(
            text = story.name,
            color = Color.White,
            fontSize = 12.sp,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}

@Composable
fun ExpandableText(name: String, text: String, maxLines: Int = 2) {
    var isExpanded by remember { mutableStateOf(false) }

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("${name} ")
        }

        withStyle(style = SpanStyle(fontSize = 16.sp, color = Color.White)) {
            append(text)
        }

        if (!isExpanded && text.count { it == '\n' } >= maxLines) {
            val expandText = " ... Show More"
            pushStringAnnotation(
                tag = "expand",
                annotation = expandText
            )
            withStyle(style = SpanStyle(fontSize = 16.sp, color = Color.Blue)) {
                append(expandText)
            }
        }
    }

    Box(
        modifier = Modifier.clickable { isExpanded = !isExpanded }
    ) {
        Text(
            text = annotatedText,
            style = TextStyle(fontSize = 16.sp, color = Color.White),
            modifier = Modifier.padding(8.dp), // Add padding here
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview() {
    Week4Theme {
        ListFeedView(
            feedList = DataSource().loadFeed(),
            storyList = DataSource().loadStory(),
            suggestionList = DataSource().loadSuggestion(),
            explore = DataSource().loadExplore()
        )
    }
}