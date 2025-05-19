package com.example.recipesaver.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class RecipeScreen {
    RecipeList,
    Bookmarks,
    FullRecipe,
}

@Composable
fun RecipeApp(
    viewModel: RecipeCardViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        // Get current back stack entry
        NavHost(
            navController = navController,
            startDestination = RecipeScreen.RecipeList.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            composable(route = RecipeScreen.RecipeList.name) {
                RecipeList(
                    recipes = viewModel.recipeCards,
                    onBookmarkPageClicked = {
                        navController.navigate(
                            route = RecipeScreen.Bookmarks.name
                        )
                    },
                    onMoreClicked = {
                        viewModel.setSelectedRecipe(it)
                        navController.navigate(
                            route = RecipeScreen.FullRecipe.name
                        )
                    }
                )
            }
            composable(route = RecipeScreen.Bookmarks.name) {
                Bookmarks(
                    recipes = viewModel.getBookmarks(),
                    clearBookmarks = {
                        viewModel.clearBookmarks()
                        navController.navigate(
                            route = RecipeScreen.Bookmarks.name
                        )
                    },
                    sort = {
                        viewModel.sortBookmarks()
                        navController.navigate(
                            route = RecipeScreen.Bookmarks.name
                        )
                    },
                    onBackPressed = {
                        navController.navigate(
                            route = RecipeScreen.RecipeList.name,
                        )
                    }
                )
            }
            composable(
                route = RecipeScreen.FullRecipe.name,
            ) {
                RecipeDetail(
                    recipeCard = viewModel.getSelectedRecipe() ?: viewModel.recipeCards.first(),
                    onAddBookmark = {
                        viewModel.toggleBookmark()
                        navController.navigate(
                            route = RecipeScreen.FullRecipe.name
                        )
                    },
                    isBookmarked = viewModel.isBookmarked(viewModel.getSelectedRecipe()),
                    onBackPressed = {
                        navController.navigate(
                            route = RecipeScreen.RecipeList.name,
                        )
                    }
                )
            }
        }
    }
}