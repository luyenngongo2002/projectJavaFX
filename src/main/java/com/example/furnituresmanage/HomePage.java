
package com.example.furnituresmanage;

import com.example.furnituresmanage.ConnectDB.DBConnection;
import com.example.furnituresmanage.ConnectDB.model.Furnitures;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePage extends Application {

    private Scene homeScene;

    public static void main(String[] args) {
        launch(args);
    }

    void showListRoom(ArrayList<Furnitures> listRoom, GridPane grid, Stage stage, GridPane root) {

        Label labelPro =new Label("DANH SÁCH SẢN PHẨM NỘI THẤT");
        labelPro.setPadding(new Insets(10, 100, 20, 500));;
        labelPro.setAlignment(Pos.CENTER);
        labelPro.setStyle("-fx-font-size: 20px; -fx-text-fill: cyan;");
        int ygrid = 0;
        int xgrid = 0;
        for (int i = 0; i < listRoom.size(); i++) {
            VBox vbRoom = new VBox();
            var btnBuy = new Button("View");
            btnBuy.setOnAction(actionEvent -> {

            });
            Image image = new Image(listRoom.get(i).getImages());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Label lbName = new Label(listRoom.get(i).getName());
            Label lbPrice = new Label(("Price: $" + String.valueOf(listRoom.get(i).getPrice())));
            vbRoom.getChildren().addAll(imageView, lbName, lbPrice, btnBuy);
            grid.add((vbRoom),xgrid, ygrid);
            xgrid += 1;
            if (xgrid == 3) {
                xgrid = 0;
                ygrid += 1;
            }

        }
    }

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        DBConnection DB = new DBConnection();
        ArrayList<Furnitures> listRoom = DB.getFurnitures();
        GridPane root = new GridPane();


        //show
        showListRoom(listRoom, grid, stage, root);

        homeScene = new Scene(grid, 1000, 800);
        stage.setScene(homeScene);
        stage.show();

    }

}