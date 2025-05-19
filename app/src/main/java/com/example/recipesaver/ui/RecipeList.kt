package com.example.recipesaver.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipesaver.R
import com.example.recipesaver.data.Ingredient
import com.example.recipesaver.data.RecipeCard
import kotlinx.coroutines.launch
import kotlin.math.ceil

const val ENTRIES_PER_PAGE = 4

@Composable
fun RecipeList(
    recipes: List<RecipeCard>,
    onBookmarkPageClicked: () -> Unit,
    onMoreClicked: (RecipeCard) -> Unit
) {
    val totalPages = ceil(recipes.size.toFloat() / ENTRIES_PER_PAGE).toInt()
    val pagerState = rememberPagerState(pageCount = { totalPages })
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeadingText(
            text = stringResource(R.string.recipe_list),
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally),
            isLarge = true
        )

        Button(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = { onBookmarkPageClicked() })
        {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Bookmarks",
            )
            Spacer(Modifier.size(4.dp))
            Text(stringResource(R.string.bookmarks))
        }

        HorizontalPager(
            state = pagerState,
        ) { page ->
            Column {
                // Calculate which items belong on this page
                val startIndex = page * ENTRIES_PER_PAGE
                val pageItems = recipes.subList(
                    startIndex,
                    minOf(startIndex + ENTRIES_PER_PAGE, recipes.size)
                )
                pageItems.forEach { recipeCard ->
                    RecipeRow(
                        recipeCard = recipeCard,
                        onMoreClicked = onMoreClicked
                    )
                }
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color.DarkGray
                } else {
                    Color.LightGray
                }
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.scrollToPage(iteration)
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun RecipeRow(
    recipeCard: RecipeCard,
    moreEnabled: Boolean = true,
    onMoreClicked: (RecipeCard) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            HeadingDetails(recipeCard)

            if (moreEnabled) {
                TextButton({
                    onMoreClicked(recipeCard)
                }
                ) {
                    Text(stringResource(R.string.more))
                }
            }

        }
    }
}

@Composable
fun HeadingDetails(recipeCard: RecipeCard) {
    // Title
    HeadingText(
        text = recipeCard.recipeName,
    )

    // Author
    recipeCard.author?.let {
        Text(
            text = it,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(1.dp)
        )
    }

    // Servings
    recipeCard.servings?.let {
        Text(text = "$it Servings")
    }
}

@Composable
fun IngredientRow(
    ingredient: Ingredient,
    quantity: Int = 1
) {
    Row {
        ingredient.amount?.let {
            Text("${it * quantity} ")
        }
        ingredient.unit?.let {
            Text("${it.abbreviation} ")
        }
        Text(ingredient.ingredientName)
    }

}

@Composable
fun DirectionRow(
    number: Int,
    direction: String,
) {
    Text("$number. $direction")
}

@Composable
fun HeadingText(
    text: String,
    modifier: Modifier = Modifier,
    isLarge: Boolean = false
) = Text(
    text = text,
    fontSize = if (isLarge) 36.sp else 24.sp,
    overflow = TextOverflow.Ellipsis,
    maxLines = 1,
    fontWeight = FontWeight.Bold,
    modifier = modifier
        .padding(2.dp)
)
