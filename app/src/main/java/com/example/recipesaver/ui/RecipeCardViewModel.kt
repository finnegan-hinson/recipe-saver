package com.example.recipesaver.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesaver.data.RecipeCard
import com.example.recipesaver.data.getRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCardViewModel @Inject constructor() : ViewModel() {
    private val _recipeCards = mutableListOf<RecipeCard>()
    private val _selectedRecipe = MutableStateFlow<RecipeCard?>(null)

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

    fun clearBookmarks() {
        _bookmarkedRecipeCards.clear()
    }

    fun sortBookmarks() {
        _bookmarkedRecipeCards.sortWith(
            compareBy { recipeCards.indexOf(it) }
        )
    }

    fun setSelectedRecipe(recipeCard: RecipeCard) {
        _selectedRecipe.value = recipeCard
    }

    fun getSelectedRecipe(): RecipeCard? =
        _selectedRecipe.value

    fun toggleBookmark() {
        getSelectedRecipe()?.let {
            if (_bookmarkedRecipeCards.contains(it)) {
                _bookmarkedRecipeCards.remove(it)
            } else {
                _bookmarkedRecipeCards.add(it)
            }
        }
    }

    fun getBookmarks(): List<RecipeCard> =
        _bookmarkedRecipeCards

    fun isBookmarked(recipeCard: RecipeCard?): Boolean = _bookmarkedRecipeCards.contains(recipeCard)
}
