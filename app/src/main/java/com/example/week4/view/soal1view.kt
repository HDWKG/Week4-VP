package com.example.week4.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.data.dummy_data
import com.example.week4.R
import com.example.week4.model.line_chat
import com.example.week4.ui.theme.Week4Theme
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun ListChatView(chatList: List<line_chat>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Chats",
            modifier = Modifier.padding(8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.turtle),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopEnd)
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
        ) {
            items(chatList) { chat ->
                ChatCard(chat)
            }
        }
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ChatCard(chat: line_chat, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val chatName = rememberUpdatedState(chat.name)

    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth().clickable {
                Toast.makeText(context, "Clicked on ${chatName.value}", Toast.LENGTH_SHORT).show()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        // Create a Column to hold the chat.name and chat.msg
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(
                text = chat.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = chat.msg,
                fontSize = 14.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                textAlign = TextAlign.Left
            )
        }

        // Create a Column to hold the chat.date
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = chat.date,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Right
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Week4Theme {
        ListChatView(chatList = dummy_data().get_data_line())
    }
}