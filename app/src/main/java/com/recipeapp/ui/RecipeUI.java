package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;

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
                        CSVDataHandler csvDataHandler = new CSVDataHandler();
                        if (csvDataHandler.readData().size() > 0){
                            System.out.println("Recipes:\n");
                            for (int i = 0; i < csvDataHandler.readData().size(); i++) {
                                System.out.println("-----------------------------------");
                                System.out.println("Recipe Name: " + csvDataHandler.readData().get(i).getName());
                                System.out.print("Main Ingredients: " + csvDataHandler.readData().get(i).getIngredients().get(0).getName());
                                for (int j = 1; j < csvDataHandler.readData().get(i).getIngredients().size(); j++) {
                                    System.out.print("," + csvDataHandler.readData().get(i).getIngredients().get(j).getName());
                                }
                            }
                        } else {
                            System.out.println("No recipes available.");
                        }

                        System.out.println("-----------------------------------");
                        break;
                    case "2":
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
}
