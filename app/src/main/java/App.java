import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

/*
 * ユーザーのモード選択入力を受け付ける
 * 選択されたモードに応じて対応したデータハンドラーインスタンスを生成
 * 選択されたデータハンドラーを基にメインメニューを表示
 * 
 */

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // ユーザーのモード選択入力を受け付ける処理
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            
            // 選択されたモードに応じて対応したデータハンドラーインスタンスを生成
            DataHandler dataHandler;
            switch (choice) {
                case "2":
                    dataHandler = new JSONDataHandler();
                    break;
            
                default:
                    dataHandler = new CSVDataHandler();
                    break;
            }

            // 選択されたデータハンドラーを基にメインメニューを表示
            RecipeUI recipeUI = new RecipeUI(dataHandler);
            recipeUI.displayMenu();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}