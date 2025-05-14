package com.example.recipesaver.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesaver.data.RecipeCard
import com.example.recipesaver.data.getRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCardViewModel @Inject constructor() : ViewModel() {
    private val _recipeCards = mutableListOf<RecipeCard>()

    init {
        viewModelScope.launch {
            getRecipes().forEach { card ->
                for (n in 0..100) {
                    _recipeCards.add(card.copy(recipeName = "$n ${card.recipeName}"))
                }
            }
        }
    }

    private val _bookmarkedRecipeCards: MutableList<RecipeCard> = mutableListOf()

    val recipeCards = _recipeCards.toList()
    val bookmarkedRecipeCards = _bookmarkedRecipeCards.toList()

    fun clearBookmarks() {
        _bookmarkedRecipeCards.clear()
    }

    fun sortBookmarks() {
        _bookmarkedRecipeCards.sortWith(compareBy { it.servings })
    }
}