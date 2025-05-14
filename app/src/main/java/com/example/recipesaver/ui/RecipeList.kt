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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesaver.R
import com.example.recipesaver.data.Ingredient
import com.example.recipesaver.data.RecipeCard
import kotlinx.coroutines.launch
import java.math.BigDecimal
import kotlin.math.ceil

const val ENTRIES_PER_PAGE = 6

@JvmOverloads
@Composable
fun RecipeList(
    viewModel: RecipeCardViewModel = hiltViewModel()
) {
    val totalPages = ceil(viewModel.recipeCards.size.toFloat() / ENTRIES_PER_PAGE).toInt()
    val pagerState = rememberPagerState(pageCount = {
        totalPages
    })
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
        HorizontalPager(
            state = pagerState,
        ) { page ->
            Column {
                // Calculate which items belong on this page
                val startIndex = page * ENTRIES_PER_PAGE
                val pageItems = viewModel.recipeCards.subList(
                    startIndex,
                    minOf(startIndex + ENTRIES_PER_PAGE, viewModel.recipeCards.size)
                )
                pageItems.forEach { recipeCard ->
                    RecipeRow(recipeCard)
                }
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
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
fun RecipeRow(recipeCard: RecipeCard) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { expanded = !expanded }
            )
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
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

            if (expanded) {
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

    }
}

@Composable
fun IngredientRow(
    ingredient: Ingredient,
    quantity: Int = 1
) {
    Row {
        ingredient.amount?.let {
            Text("${it * BigDecimal(quantity)} ")
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
