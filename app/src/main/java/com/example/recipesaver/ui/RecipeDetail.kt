package com.example.recipesaver.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipesaver.data.RecipeCard


@Composable
fun RecipeDetail(
    recipeCard: RecipeCard,
    onAddBookmark: (RecipeCard) -> Unit,
    isBookmarked: Boolean,
    onBackPressed: () -> Unit
) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = Modifier.padding(30.dp)
    ) {

        HeadingDetails(recipeCard)

        Button(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = { onAddBookmark(recipeCard) }) {
            Icon(
                imageVector = if (isBookmarked) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                contentDescription = "Bookmark Icon",
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 3.dp))

        // Ingredients
        Column(
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            recipeCard.ingredients.forEach { ingredient ->
                IngredientRow(ingredient)
            }
        }
        // Directions
        Column(
            modifier = Modifier.padding(vertical = 3.dp)
        ) {
            recipeCard.directions.forEachIndexed { index, direction ->
                DirectionRow(
                    direction = direction,
                    number = index + 1
                )
            }
        }
    }
}
