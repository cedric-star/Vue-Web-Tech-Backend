schreibe zwei json datein, sie sollen nur aus einem json array mit verschiedenen rezepten als einträgen. nach folgendem format: [
{
"process": "Cut the chicken into small pieces and fry it in a pan. Add the diced onions and bell peppers and fry them together. Pour in the coconut milk and curry paste and let everything simmer until the chicken is cooked through.",
"additives": "meat",
"name": "Chicken Curry",
"ingredients": "Chicken breast (500g), Onion (1), Bell pepper (2), Coconut milk (400ml), Curry paste (2 tbsp)",
"type": "cooking"
}
] schreibe 8 einträge pro datei, alles in englisch und setze in der einen datei überall type auf cooking und in der anderen auf baking schreibe also baking und cooking rezepte. additives sind nur zu setzen, wennes zusatzstoffe gibt oder sachen zum wissen wie vegan oder vegetarisch
ChatGPT:
ChatGPT
cooking.json
json
Code kopieren
[
{
"process": "Cut the chicken into small pieces and fry it in a pan. Add the diced onions and bell peppers and fry them together. Pour in the coconut milk and curry paste and let everything simmer until the chicken is cooked through.",
"additives": "meat",
"name": "Chicken Curry",
"ingredients": "Chicken breast (500g), Onion (1), Bell pepper (2), Coconut milk (400ml), Curry paste (2 tbsp)",
"type": "cooking"
},
{
"process": "Boil the pasta in salted water until al dente. In a pan, sauté garlic and chili flakes in olive oil. Add cooked pasta and toss to coat. Garnish with fresh parsley.",
"additives": "vegetarian",
"name": "Spaghetti Aglio e Olio",
"ingredients": "Spaghetti (200g), Garlic (3 cloves), Chili flakes (1 tsp), Olive oil (2 tbsp), Parsley (fresh, chopped)",
"type": "cooking"
},
{
"process": "Heat the oil in a pot and fry diced onions and garlic. Add the lentils, spices, and vegetable broth. Simmer until the lentils are soft and the soup is thickened.",
"additives": "vegan",
"name": "Lentil Soup",
"ingredients": "Red lentils (200g), Onion (1), Garlic (2 cloves), Vegetable broth (1L), Cumin (1 tsp), Paprika (1 tsp)",
"type": "cooking"
},
{
"process": "Marinate the salmon fillets with soy sauce, honey, and garlic. Bake in the oven at 200°C for 15-20 minutes until cooked through.",
"additives": "fish",
"name": "Honey Soy Salmon",
"ingredients": "Salmon fillets (2), Soy sauce (2 tbsp), Honey (1 tbsp), Garlic (1 clove, minced)",
"type": "cooking"
},
{
"process": "Fry diced potatoes in a pan until golden and crispy. Add diced onions, bacon, and season with salt and pepper. Cook until everything is browned.",
"additives": "meat",
"name": "Pan-Fried Potatoes",
"ingredients": "Potatoes (500g), Onion (1), Bacon (100g), Salt (to taste), Pepper (to taste)",
"type": "cooking"
},
{
"process": "Mix diced tomatoes, garlic, and olive oil in a pot. Cook on low heat for 30 minutes. Blend until smooth and serve with cooked spaghetti.",
"additives": "vegan",
"name": "Tomato Sauce",
"ingredients": "Tomatoes (500g), Garlic (2 cloves), Olive oil (2 tbsp), Basil (fresh, optional)",
"type": "cooking"
},
{
"process": "Stir-fry tofu cubes in sesame oil until crispy. Add soy sauce, ginger, and garlic. Serve with steamed vegetables and rice.",
"additives": "vegan",
"name": "Tofu Stir-Fry",
"ingredients": "Tofu (300g), Sesame oil (1 tbsp), Soy sauce (2 tbsp), Ginger (1 tsp, grated), Garlic (2 cloves), Vegetables (steamed)",
"type": "cooking"
},
{
"process": "Fry chopped spinach and garlic in olive oil. Mix with cooked pasta and top with grated Parmesan cheese.",
"additives": "vegetarian",
"name": "Spinach Pasta",
"ingredients": "Spinach (300g), Garlic (2 cloves), Olive oil (2 tbsp), Pasta (200g), Parmesan cheese (grated, to taste)",
"type": "cooking"
}
]
baking.json
json
Code kopieren
[
{
"process": "Mix flour, sugar, and baking powder in a bowl. Add butter and eggs and mix until smooth. Pour into a loaf pan and bake at 180°C for 40 minutes.",
"additives": null,
"name": "Vanilla Pound Cake",
"ingredients": "Flour (250g), Sugar (200g), Baking powder (1 tsp), Butter (200g), Eggs (3)",
"type": "baking"
},
{
"process": "Combine flour, sugar, cocoa powder, baking powder, and milk. Pour the mixture into a muffin tray and bake at 180°C for 20 minutes.",
"additives": "vegetarian",
"name": "Chocolate Muffins",
"ingredients": "Flour (200g), Sugar (150g), Cocoa powder (50g), Baking powder (1 tsp), Milk (200ml)",
"type": "baking"
},
{
"process": "Mix rolled oats, honey, and melted butter. Press the mixture into a baking dish and bake at 160°C for 25 minutes until golden.",
"additives": "vegetarian",
"name": "Oat Bars",
"ingredients": "Rolled oats (300g), Honey (100g), Butter (100g, melted)",
"type": "baking"
},
{
"process": "Mix flour, yeast, sugar, salt, and water to form a dough. Let rise for 1 hour, then bake at 200°C for 25 minutes.",
"additives": "vegan",
"name": "Rustic Bread",
"ingredients": "Flour (500g), Yeast (7g), Sugar (1 tsp), Salt (1 tsp), Water (300ml)",
"type": "baking"
},
{
"process": "Whisk eggs and sugar until fluffy. Add flour and vanilla extract. Pour into a pan and bake at 180°C for 20 minutes.",
"additives": "vegetarian",
"name": "Vanilla Sponge Cake",
"ingredients": "Eggs (4), Sugar (150g), Flour (100g), Vanilla extract (1 tsp)",
"type": "baking"
},
{
"process": "Combine apples, sugar, and cinnamon. Place in a baking dish and cover with a mix of oats, butter, and sugar. Bake at 180°C for 30 minutes.",
"additives": "vegetarian",
"name": "Apple Crumble",
"ingredients": "Apples (4), Sugar (100g), Cinnamon (1 tsp), Oats (100g), Butter (100g)",
"type": "baking"
},
{
"process": "Mix cream cheese, sugar, and eggs. Pour over a graham cracker crust and bake at 160°C for 40 minutes.",
"additives": "vegetarian",
"name": "Cheesecake",
"ingredients": "Cream cheese (400g), Sugar (150g), Eggs (3), Graham crackers (200g)",
"type": "baking"
},
{
"process": "Combine flour, butter, sugar, and water to form a dough. Roll out and place in a pie dish. Add filling and bake at 180°C for 35 minutes.",
"additives": "vegetarian",
"name": "Fruit Pie",
"ingredients": "Flour (250g), Butter (150g), Sugar (50g), Water (2 tbsp), Fruit filling (500g)",
"type": "baking"
}
]´´