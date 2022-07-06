
package com.example.furnituresmanage;

import com.example.furnituresmanage.ConnectDB.DBConnection;
import com.example.furnituresmanage.ConnectDB.model.Furnitures;
import com.example.furnituresmanage.ConnectDB.model.Order;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

//import static jdk.vm.ci.aarch64.AArch64.sp;

public class HomePage extends Application {

    private Scene homeScene, viewScene;

    public static void main(String[] args) {
        launch(args);
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
            btnView.setStyle("-fx-font: 10px ; -fx-base: #00CC66;");
            int finalI = i;
            btnView.setOnAction(actionEvent -> {
                showView(finalI,listFurnitures);
            });
            Image image = new Image(listFurnitures.get(i).getImages());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            Label lbName = new Label(listFurnitures.get(i).getName());
            Label lbPrice = new Label(("Price: $" + String.valueOf(listFurnitures.get(i).getPrice())));
            vbHome.getChildren().addAll(imageView, lbName, lbPrice, btnView);
            lbName.setStyle("-fx-font-size: 17px; -fx-text-fill: #3425AF;");

//          =============================


        }
        homePage.getChildren().addAll(labelPro, grid);
    }
    void showView(int id ,ArrayList<Furnitures> listFurnitures){
        VBox viewBig = new VBox();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 0, 0, 20));

        Image image = new Image(listFurnitures.get(id).getImages());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(350);
        Label lbName = new Label(listFurnitures.get(id).getName());
        Label lbSource = new Label( "Nguồn gốc: "+listFurnitures.get(id).getSource());
        Label lbPrice = new Label(("Price: $" + String.valueOf(listFurnitures.get(id).getPrice())));
        Label lbQuantity = new Label(("Số lượng sản phẩm: " + String.valueOf(listFurnitures.get(id).getQuantity())));
        var btnAddCard = new Button("Add to card");
        btnAddCard.setOnAction( actionEvent ->
                showAler()
        );

        btnAddCard.setStyle(" -fx-base: #961276;");
        viewBig.getChildren().addAll(lbName,lbSource, lbPrice, lbQuantity, btnAddCard);
        lbName.setStyle("-fx-font-size: 25px; -fx-text-fill: #0076a3;");
        viewBig.setStyle("-fx-font-size: 15px; -fx-text-fill: #CC99CC;");

        grid.add((imageView),0, 0);
        grid.add((viewBig),1, 0);
        viewBig.setPadding(new Insets(30, 70, 50, 20));;
        viewScene = new Scene(grid,700,500);

        Stage  viewStage= new Stage();

        viewStage.setScene(viewScene);
        viewStage.show();

    }
    private void showAler() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setContentText("Add to cart successfully!");

        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) {
        VBox homePage = new VBox();
//        VBox homeCard = new VBox();
        GridPane grid = new GridPane();
        DBConnection DB = new DBConnection();
        ArrayList<Furnitures> listProduct = DB.getFurnitures();


        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(20);
        grid.setHgap(20);


        showListFurnitures(listProduct, grid, stage, homePage);
        homeScene = new Scene(homePage, 1000, 800);

        stage.setScene(homeScene);
        stage.show();

    }



}