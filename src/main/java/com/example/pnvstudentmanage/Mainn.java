package com.example.pnvstudentmanage;

import com.example.pnvstudentmanage.ConnectDB.DBConnection;
import com.example.pnvstudentmanage.ConnectDB.model.Furnitures;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
//import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;
import java.sql.*;


public class Mainn extends Application {

    public Scene scene;
    private static final String EMPTY = "";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        DBConnection con = new DBConnection();
        ArrayList<Furnitures> ntList = con.getFurnitures();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        //
        grid.add(new Label("Name:"), 0, 0);
        var tfName = new TextField();
        grid.add(tfName, 0, 1);
        //
        grid.add(new Label("Source:"), 1, 0);
        var tfSource = new TextField();
        grid.add(tfSource, 1, 1);
        //
        grid.add(new Label("Quantity:"), 2, 0);
        var tfQuantity = new TextField();
        grid.add(tfQuantity, 2, 1);
        //
        grid.add(new Label("Price:"), 3, 0);
        var tfPrice = new TextField();
        grid.add(tfPrice, 3, 1);
        //
        grid.add(new Label("Images:"), 4, 0);
        var tfImages = new TextField();
        grid.add(tfImages, 4, 1);

        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String source = tfSource.getText();
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            Float price = Float.parseFloat(tfPrice.getText());
            String images = tfImages.getText();

            if (!name.equals(EMPTY) && !source.equals(EMPTY)  && !quantity.equals(EMPTY) && !price.equals(EMPTY) && !images.equals(EMPTY) ) {
                con.insertFurnitures(new Furnitures(name, source, quantity, price, images));
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank!");
            alert.showAndWait();
        });
        grid.add(btnAdd, 5, 1);


//Show
        for (int i = 0; i < ntList.size(); i++) {
            Image image = new Image(ntList.get(i).getImages());
            System.out.print(image);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(110);
            System.out.println(ntList.get(i).getName());
            grid.add(new Label(ntList.get(i).getName()), 0, i + 2);
            grid.add(new Label(ntList.get(i).getSource()), 1, i + 2);
            grid.add(new Label("" + ntList.get(i).getQuantity()), 2, i + 2);
            grid.add(new Label("" +ntList.get(i).getPrice()), 3, i + 2);
            grid.add((imageView), 4, i + 2);
//            Image image = new Image(((Furnitures)ntList.get(i)).getImages());//
//            ImageView imageView = new ImageView();
//            imageView.setImage(image);
//            imageView.setFitWidth(110.0);
//            imageView.setFitHeight(110.0);

// Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            ArrayList<Furnitures> finalNtList = ntList;
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int idNew = Integer.parseInt(btnUpdate.getId());
                System.out.println(idNew);

                TextField name = (TextField) grid.getChildren().get(1);
                name.setText(finalNtList.get(idNew).getName());

                TextField source = (TextField) grid.getChildren().get(3);
                source.setText(finalNtList.get(idNew).getSource());

                TextField quantity = (TextField) grid.getChildren().get(5);
                quantity.setText("" + finalNtList.get(idNew).getQuantity());

                TextField price = (TextField) grid.getChildren().get(7);
                price.setText("" + finalNtList.get(idNew).getPrice());

                TextField images = (TextField) grid.getChildren().get(9);
                images.setText(finalNtList.get(idNew).getImages());

                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(event -> {
                    int newID = finalNtList.get(idNew).id_fur;
                    System.out.println(newID);
                    String newName = name.getText();
                    String newSource = source.getText();

                    int newQuantity  =  0;
                    try {
                        newQuantity  = Integer.parseInt(quantity.getText());
                    }catch (Exception ex){}

                    float newPrice = 0;
                    try {
                        newPrice = Float.parseFloat(price.getText());
                    }
                    catch (Exception ex){}

                    String newImages = images.getText();

//                    System.out.println(newName+ " " + newSource + " " + newQuantity + " " + newPrice + " " + newImages);


                    if (!newName.equals(EMPTY) && !newSource.equals(EMPTY) && (newQuantity  != 0) && (newPrice!=0) && !newImages.equals(EMPTY)) {
                        try {

                            con.updateFurnitures(new Furnitures(newID, newName, newSource, newQuantity, newPrice, newImages));
                            start(stage);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        return;
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all blank!");
                        alert.showAndWait();
                    }
                });
                grid.add(newbtnAdd, 5, 1);
//                var alert = new Alert(Alert.AlertType.INFORMATION);
            });
            grid.add(btnUpdate, 6, i + 2);
// Delete

            Button btnDelete = new Button("Delete");
            btnDelete.setId(String.valueOf(((Furnitures)ntList.get(i)).id_fur));
            btnDelete.setOnAction(e -> {
                int idDelete =Integer.parseInt(btnDelete.getId());
                con.deleteFurnitures(idDelete);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btnDelete, 5, i + 2);
        }

        Scene scene = new Scene(grid, 1500,600);
        stage.setTitle("Painting");
        stage.setScene(scene);
        stage.show();

    }
}

