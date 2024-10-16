package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.*;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }

    }

    /**
     * レシピデータを表示するメソッド
     * レシピデータを取得
     * レシピデータの有無判定
     *  データ有りの場合:
     *      データをレシピ名と材料名をそれぞれ表示
     *  データ無しの場合:
     *      データがないことを表示
    */ 
    private void displayRecipes() {
        try {
            if (dataHandler.readData().size() > 0){
                System.out.println("Recipes:");
                for (int i = 0; i < dataHandler.readData().size(); i++) {
                    System.out.println("-----------------------------------");
                    System.out.println("Recipe Name: " + dataHandler.readData().get(i).getName());
                    String[] str = new String[dataHandler.readData().get(i).getIngredients().size()];
                    for (int j = 0; j < dataHandler.readData().get(i).getIngredients().size(); j++) {
                        str[j] = dataHandler.readData().get(i).getIngredients().get(j).getName();
                    }
                    System.out.println("Main Ingredients: " + String.join(",", str));
                }
                System.out.println("-----------------------------------");

            } else {
                System.out.println("No recipes available.");
            }
    
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }
    /*
     * ユーザのレシピ名入力を受け付ける
     * 入力されたレシピ名を変数に格納する
     * ユーザの材料名入力をdoneが入力されるまで以下処理を繰り返す
     *  ユーザの材料名入力を受け付ける
     *  入力された材料名を材料リスト変数に格納する
     * レシピデータを書き込むメソッドを呼び出す
     */
    private void addNewRecipe() {
        try {
            System.out.println("\nAdding a new recipe.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.println("Enter ingredients (type 'done' when finished):");
            String ingredientName = "";
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while (!(ingredientName.equals("done"))) {
                System.out.print("Ingredient: ");
                ingredientName = reader.readLine();
                ingredients.add(new Ingredient(ingredientName));
            }
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
