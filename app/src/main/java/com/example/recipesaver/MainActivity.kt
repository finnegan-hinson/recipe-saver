package com.example.recipesaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.recipesaver.ui.RecipeCardViewModel
import com.example.recipesaver.ui.RecipeList
import com.example.recipesaver.ui.theme.RecipeSaverTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeSaverTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RecipeList()
                }
            }
        }
    }
}