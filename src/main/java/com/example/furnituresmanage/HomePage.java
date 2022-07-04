
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

    private Scene homeScene, viewScene;

    public static void main(String[] args) {
        launch(args);
    }

    void showView(int id ,ArrayList<Furnitures> listFurnitures){
        System.out.println(id);
        VBox viewBig = new VBox();
        GridPane grid = new GridPane();

        Image image = new Image(listFurnitures.get(id).getImages());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        Label lbName = new Label(listFurnitures.get(id).getName());
        Label lbSource = new Label( "Nguồn gốc: "+listFurnitures.get(id).getSource());
        Label lbPrice = new Label(("Price: $" + String.valueOf(listFurnitures.get(id).getPrice())));
        Label lbQuantity = new Label(("Số lượng sản phẩm: " + String.valueOf(listFurnitures.get(id).getQuantity())));
        var btnAddCard = new Button("Add to card");
        btnAddCard.setStyle("-fx-font: 10px ; -fx-base: #961276;");



        viewBig.getChildren().addAll(lbName,lbSource, lbPrice, lbQuantity, btnAddCard);
        lbName.setStyle("-fx-font-size: 30px; -fx-text-fill: #0076a3");
        viewBig.setStyle("-fx-font-size: 15px; -fx-text-fill: #0076a3");


        grid.add((imageView),0, 0);
        grid.add((viewBig),1, 0);
        viewBig.setPadding(new Insets(30, 70, 50, 20));;
        viewScene = new Scene(grid,500,500);


        Stage  viewStage= new Stage();

        viewStage.setScene(viewScene);
        viewStage.show();

    }
    void showListFurnitures(ArrayList<Furnitures> listFurnitures, GridPane grid, Stage stage,  VBox homePage) {
        GridPane root = new GridPane();
        Label labelPro =new Label("DANH SÁCH SẢN PHẨM ");
        labelPro.setPadding(new Insets(10, 100, 20, 500));;
        labelPro.setAlignment(Pos.CENTER);
        labelPro.setStyle("-fx-font-size: 20px; -fx-text-fill: 3425AF;");

        int ygrid = 0;
        int xgrid = 0;
        for (int i = 0; i < listFurnitures.size(); i++) {
            VBox vbHome = new VBox();
            var btnView = new Button("View");
            btnView.setStyle("-fx-font: 10px ; -fx-base: #961276;");
            int finalI = i;
            btnView.setOnAction(actionEvent -> {
                int id = listFurnitures.get(finalI).getId_fur();
                showView(id,listFurnitures);

            });
            Image image = new Image(listFurnitures.get(i).getImages());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            Label lbName = new Label(listFurnitures.get(i).getName());
            Label lbPrice = new Label(("Price: $" + String.valueOf(listFurnitures.get(i).getPrice())));
            vbHome.getChildren().addAll(imageView, lbName, lbPrice, btnView);
            lbName.setStyle("-fx-font-size: 17px; -fx-text-fill: #3425AF");

            grid.add((vbHome),xgrid, ygrid);
            xgrid += 1;
            if (xgrid == 3) {
                xgrid = 0;
                ygrid += 1;
            }
        }
        homePage.getChildren().addAll(labelPro, grid);
    }

    @Override
    public void start(Stage stage) {
        VBox homePage = new VBox();
        GridPane grid = new GridPane();
        DBConnection DB = new DBConnection();
        ArrayList<Furnitures> listRoom = DB.getFurnitures();

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(20);
        grid.setHgap(20);




        //show
        showListFurnitures(listRoom, grid, stage,  homePage);

        homeScene = new Scene(homePage, 1000, 800);
        stage.setScene(homeScene);
        stage.show();

    }

}