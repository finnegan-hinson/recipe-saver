package com.example.recipesaver.data

import java.math.BigDecimal

data class RecipeCard(
    val recipeName: String,
    val ingredients: List<Ingredient>,
    val servings: Int?,
    val introduction: String?,
    val author: String?,
    val directions: List<String>,
)

/**
 * As read in a typical recipe {amount} {unit} {ingredientName}
 * 2, null, eggs -> 2 eggs
 * 4.5, Tbs, honey -> 4.5 Tbs honey
 */
data class Ingredient(
    val ingredientName: String,
    val amount: BigDecimal? = null,
    val unit: Unit? = null,
)

enum class Unit(
    val isVolumetric: Boolean,
    val abbreviation: String
) {
    TABLESPOON(isVolumetric = true, abbreviation = "Tbs"),
    TEASPOON(isVolumetric = true, abbreviation = "tsp"),
    CUP(isVolumetric = true, abbreviation = "cup"),
    GRAM(isVolumetric = false, abbreviation = "g"),
    OUNCE(isVolumetric = false, abbreviation = "oz"),
    POUND(isVolumetric = false, abbreviation = "lb")
}