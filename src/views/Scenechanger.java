/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author harma
 */
public class Scenechanger {
    
    public void scenechange(ActionEvent push, String viewName, String title) throws IOException
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(viewName));
      Parent parent = loader.load();
      
      Scene scene = new Scene(parent);
      
      //stage that was passed
      Stage stage = (Stage)((Node)push.getSource()).getScene().getWindow();
      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
    }
}
