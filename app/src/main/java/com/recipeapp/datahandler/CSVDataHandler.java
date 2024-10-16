package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app//src//main//resources//recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    // 現在のモードを返します。
    @Override
    public String getMode() {
        return "CSV";
    }

    // レシピデータを読み込み、Recipeオブジェクトのリストとして返します。
    /*
     * recipes.csvからレシピデータを読み込む
     * レシピデータをリスト形式に変換する
     * レシピデータを返却
     */
    @Override
    public ArrayList<Recipe> readData() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String recipeName;
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            ArrayList<Recipe> recipeData = new ArrayList<>();
            String[] str;

            while((line = reader.readLine()) != null) {
                names.add(line);
            }
            for (int i = 0; i < names.size(); i++) {
                str = names.get(i).split(",");
                recipeName = str[0];
                for (int j = 1; j < str.length; j++) {
                    ingredients.add(new Ingredient(str[j]));

                }
                recipeData.add(new Recipe(recipeName, ingredients));
            }
            reader.close();
        return recipeData;
}

    // 指定されたRecipeオブジェクトを追加します。
    @Override
    public void writeData(Recipe recipe) {

    }

    // 指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
