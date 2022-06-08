//package com.example.pnvstudentmanage;
//
//import com.example.pnvstudentmanage.ConnectDB.DBConnection;
//import com.example.pnvstudentmanage.ConnectDB.model.Furnitures;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        DBConnection db= new DBConnection();
//        VBox vBox =new VBox();
//
//        getThenDisplayFurnitures(vBox, db);
//        //tao arr, truyen doi tuong,
////        ArrayList<Students> stdlist = db.getStudents();
////        System.out.println("Size:"+ stdlist.size());
//
////        db.insertStudent(new Students("lan", 10));
////       db.updateStudent(1, new Students( "Luyen",4));
////        db.deleteStudent(1);
//
//        Scene scene = new Scene(vBox, 700, 550);
//        stage.setTitle("Table View Sample");
////
//        stage.setScene(scene);
//        stage.show();
//    }
//    //doc ds students
//    void getThenDisplayFurnitures(VBox vBox, DBConnection db){
//        ArrayList<Furnitures> stdlist = db.getFurnitures();
//        System.out.println("Size:"+ stdlist.size());
//        for (int i =0;  i< stdlist.size(); i++){
//            HBox hBoxStudent = new HBox();
//            hBoxStudent.setSpacing(30);
//
//
//            Label lbId = new Label(""+stdlist.get(i).id_fur);
//            Label lbName = new Label(""+stdlist.get(i).name);
//            Label lbSource = new Label(""+stdlist.get(i).source);
//            Label lbQuantity = new Label(""+stdlist.get(i).quantity);
//            Label lbPrice= new Label(""+stdlist.get(i).price);
//            Button btnDelete = new Button("Delete");
//
//            btnDelete.setId(String.valueOf(stdlist.get(i).id_fur));
//            btnDelete.setOnAction(actionEvent ->
//                    db.deleteFurnitures(Integer.parseInt(btnDelete.getId())));
//            hBoxStudent.getChildren().addAll( lbId,lbName,lbSource, lbQuantity,lbPrice,  btnDelete);
//            vBox.getChildren().add(hBoxStudent);
//
//        }
//
//    }
//    public static void main(String[] args) {
//        launch();
//    }
//}