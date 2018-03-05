/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author harma
 */
public class launchContacts extends Application {
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("newcontact.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Contact Table");
        primaryStage.setScene(scene);
primaryStage.show();    }
    
}
