package com.example.recipesaver.data

import java.math.BigDecimal

/**
 * https://cooking.nytimes.com/recipes/1025123-macaroni-and-peas example recipe
 */
fun getRecipes(): MutableList<RecipeCard> = mutableListOf(
    RecipeCard(
        recipeName = "Macaroni and Peas",
        ingredients = listOf(
            Ingredient(
                ingredientName = "grated Parmesan",
                amount = 2.toDouble(),
                unit = Unit.OUNCE
            ),
            Ingredient(
                ingredientName = "whole large eggs",
                amount = 2.toDouble(),
            ),
            Ingredient(
                ingredientName = "whole large eggs",
                amount = 2.toDouble(),
            ),
            Ingredient(
                ingredientName = "heavy cream",
                amount = 0.5,
                unit = Unit.CUP
            ),
            Ingredient(
                ingredientName = "salt and black pepper",
            ),
            Ingredient(
                ingredientName = "dry pasta",
                amount = 1.toDouble(),
                unit = Unit.POUND
            ),
            Ingredient(
                ingredientName = "frozen peas",
                amount = 1.toDouble(),
                unit = Unit.CUP
            ),
            Ingredient(
                ingredientName = "canola or vegetable oil",
                amount = 1.toDouble(),
                unit = Unit.TEASPOON
            ),
            Ingredient(
                ingredientName = "bacon or pancetta",
                amount = 3.toDouble(),
                unit = Unit.OUNCE
            ),
            Ingredient(
                ingredientName = "scallions",
                amount = 3.toDouble(),
            ),
            Ingredient(
                ingredientName = "garlic cloves",
                amount = 3.toDouble(),
            ),
            Ingredient(
                ingredientName = "parsley and mint",
                amount = 1.toDouble(),
                unit = Unit.CUP
            ),
        ),
        servings = 4,
        introduction = "This recipe starts with a love of store-bought mac and cheese, amplified " +
                "with frozen peas and diced ham. But then, it adds a few layers of flavor, " +
                "increasing the peas, sautéing the cured pork and using a from-scratch garlicky " +
                "Parmesan sauce inspired by classic pasta paglia e fieno (“straw and hay pasta,” " +
                "so named because it’s typically made with a combination of plain and green " +
                "fettuccine pastas that resembles fresh and dried grass). While pasta paglia e " +
                "fieno typically uses reduced heavy cream as its sauce, this recipe keeps it a " +
                "little lighter by decreasing the amount of cream and instead relying on eggs to " +
                "give the sauce its clingy, glossy texture, like in a good carbonara. A finish " +
                "of parsley and mint further lightens it.",
        author = "J. Kenji López-Alt",
        directions = listOf(
            "Bring a medium pot of salted water to a boil over high heat, and while you wait, " +
                    "make the sauce: Whisk together the grated cheese, eggs and cream in a large " +
                    "bowl and set aside.",
            "Add the pasta to the boiling water, give it a stir and let it cook until mostly " +
                    "done but still slightly chalky in the center, about 2 minutes less than the " +
                    "package directions. Add the frozen peas and cook until defrosted, about " +
                    "1 minute longer. Drain, reserving at least 2 cups of the starchy pasta water.",
            "While the pasta cooks, heat the oil and bacon or pancetta in a large straight-sided " +
                    "skillet or saucepan over medium-high. Cook, stirring frequently, until the " +
                    "bacon browns at the edges, about 4 minutes. Add the scallions whites, the " +
                    "garlic, a few generous grinds of black pepper and a pinch of salt. Cook," +
                    " stirring, until the garlic and scallions are lightly softened and fragrant," +
                    " about 30 seconds longer. Remove from heat and, using a ladle, add ½ cup of " +
                    "the pasta water to the pan to halt cooking.",
            "When the pasta is cooked, add the drained pasta, peas and a half cup of the reserved" +
                    " pasta water to the bowl with the cream sauce. Stir with tongs to thoroughly" +
                    " combine, then transfer everything back to the skillet with the bacon," +
                    " scallion whites, and garlic. Set over low heat and cook, stirring and " +
                    "tossing continuously, until the sauce thickens lightly and coats the pasta, " +
                    "about 2 minutes. (If it looks too dry, add more reserved pasta water, stir" +
                    " and toss vigorously, bearing in mind that it will get drier once it hits " +
                    "the plate and starts to cool, so it should look a little too loose in the pan.)",
            "Stir in parsley, mint and scallion greens, keeping a handful of herbs for garnish. " +
                    "Season to taste with salt and pepper. Transfer to a serving bowl, sprinkle " +
                    "with reserved herbs and cheese, and serve immediately."
        ),
    )
)
