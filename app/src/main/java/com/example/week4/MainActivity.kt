package com.example.week4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week4.data.DataSource
import com.example.week4.data.dummy_data
import com.example.week4.ui.theme.Week4Theme
import com.example.week4.view.ListExploreView
import com.example.week4.view.ListFeedView
import com.example.week4.view.ListTokpedView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    ListFeedView(
                        feedList = DataSource().loadFeed(),
                        storyList = DataSource().loadStory(),
                        suggestionList = DataSource().loadSuggestion(),
                        explore = DataSource().loadExplore()
                    )

                }
            }
        }
    }
}
