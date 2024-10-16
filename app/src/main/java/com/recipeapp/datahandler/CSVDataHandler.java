package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    // 
    /**
     * レシピデータを読み込み、Recipeオブジェクトのリストとして返します。
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
            ArrayList<Recipe> recipeData = new ArrayList<>();
            String[] str;

            while((line = reader.readLine()) != null) {
                names.add(line);
            }
            for (int i = 0; i < names.size(); i++) {
                str = names.get(i).split(",");
                recipeName = str[0];
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int j = 1; j < str.length; j++) {
                    ingredients.add(new Ingredient(str[j]));

                }
                recipeData.add(new Recipe(recipeName, ingredients));
            }
            reader.close();
        return recipeData;
}
    /*
     * 指定されたRecipeオブジェクトを追加します。
     * ファイル書き込みインスタンスを生成
     * 引数で渡されたレシピの材料データを整形
     * レシピ名と材料名をrecipe.csvにカンマ区切りで書き込み
     */
    @Override
    public void writeData(Recipe recipe) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        String[] str = new String[recipe.getIngredients().size()];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            str[i] = recipe.getIngredients().get(i).getName();
        }
        writer.write(recipe.getName() + "," + String.join(",", str));
        writer.newLine();
        writer.close();
    }

    // 指定されたキーワードでレシピを検索し、一致するRecipeオブジェクトのリストを返します。
    @Override
    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }

}
