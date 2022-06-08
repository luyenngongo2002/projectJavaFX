package com.example.pnvstudentmanage;

import com.example.pnvstudentmanage.ConnectDB.DBConnection;
import com.example.pnvstudentmanage.ConnectDB.model.Furnitures;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;
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
        ArrayList<Furnitures> ntList;
        ntList = con.getFurnitures();
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

        var btnAdd = new Button("Add");
        btnAdd.setPadding(new Insets(5, 15, 5, 15));
        btnAdd.setOnAction(e -> {
            String name = tfName.getText();
            String source = tfSource.getText();
            Integer quantity = Integer.valueOf(tfQuantity.getText());
            Float price = Float.parseFloat(tfPrice.getText());

            if (!name.equals(EMPTY) && !source.equals(EMPTY)  && !quantity.equals(EMPTY) && !price.equals(EMPTY)) {
                con.insertFurnitures(new Furnitures(name, source, quantity, price));
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
        grid.add(btnAdd, 4, 1);

//Show
        for (int i = 0; i < ntList.size(); i++) {
            System.out.println(ntList.get(i).getName());
            grid.add(new Label(ntList.get(i).getName()), 0, i + 2);
            grid.add(new Label(ntList.get(i).getSource()), 1, i + 2);
            grid.add(new Label("" + ntList.get(i).getQuantity()), 2, i + 2);
            grid.add(new Label("" +ntList.get(i).getPrice()), 3, i + 2);

            // Update
            var btnUpdate = new Button("Update");
            btnUpdate.setId(String.valueOf(i));
            ArrayList<Furnitures> finalNtList = ntList;
            btnUpdate.setOnAction(e -> {
                btnAdd.setVisible(false);
                int idNew = Integer.parseInt(btnUpdate.getId());
                System.out.println(idNew);
                TextField a = (TextField) grid.getChildren().get(1);
                a.setText(finalNtList.get(idNew).getName());

                TextField b = (TextField) grid.getChildren().get(3);
                b.setText(finalNtList.get(idNew).getSource());

                TextField c = (TextField) grid.getChildren().get(5);
                c.setText("" + finalNtList.get(idNew).getQuantity());

                TextField d = (TextField) grid.getChildren().get(7);
                d.setText("" + finalNtList.get(idNew).getPrice());

                var newbtnAdd = new Button("Update");
                newbtnAdd.setPadding(new Insets(5, 15, 5, 15));
                newbtnAdd.setOnAction(event -> {
                    int ii = finalNtList.get(idNew).id_fur;
                    System.out.println(ii);
                    String nameNew = a.getText();
                    String sourceNew = b.getText();

                    int quantityNew =  0;
                    try {
                        quantityNew = Integer.parseInt(c.getText());
                    }catch (Exception ex){}

                    float priceNew = 0;
                    try {
                        priceNew = Float.parseFloat(d.getText());
                    }
                    catch (Exception ex){}
                    System.out.println(nameNew + " " + sourceNew + " " + quantityNew + " " + priceNew );
                    if (!nameNew.equals(EMPTY) && !sourceNew.equals(EMPTY) && (quantityNew != 0) && (priceNew!=0)) {
                        try {
                            start(stage);
                            con.updateFurnitures(new Furnitures(ii, nameNew, sourceNew, quantityNew, priceNew));
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        return;
                    }

                    var alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank!");
                    alert.showAndWait();
                });
                grid.add(newbtnAdd, 4, 1);
//                //
                var alert = new Alert(Alert.AlertType.INFORMATION);
            });
            grid.add(btnUpdate, 4, i + 2);
            // Delete

            var btn = new Button("Delete");
            btn.setId(String.valueOf(i));
            btn.setOnAction(e -> {
                con.deleteFurnitures(Integer.parseInt(btn.getId()));

                var alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setHeaderText(null);
                alert.setContentText("Deleted!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid.add(btn, 5, i + 2);
        }

        Scene scene = new Scene(grid, 1000,600);
        stage.setTitle("Painting");
        stage.setScene(scene);
        stage.show();

    }


}

