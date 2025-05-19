package com.example.recipesaver.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipesaver.R
import com.example.recipesaver.data.RecipeCard

@Composable
fun Bookmarks(
    recipes: List<RecipeCard>,
    clearBookmarks: () -> Unit,
    sort: () -> Unit,
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        HeadingText(text = stringResource(R.string.bookmarks))

        recipes.forEach { bookmark ->
            RecipeRow(
                bookmark,
                moreEnabled = false,
                onMoreClicked = {}
            )
        }

        TextButton(sort) {
            Text("Sort Bookmarks")
        }
        TextButton(clearBookmarks) {
            Text("Clear Bookmarks")
        }
    }
}
