/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import models.contacts;

/**
 * FXML Controller class
 *
 * @author harma
 */
public class NewcontactController implements Initializable {

     @FXML private TextField firstNameTextField;
     @FXML private TextField lastNameTextField;
     @FXML private TextField phoneNumberTextField;
     @FXML private TextField addressTextField;
     @FXML private DatePicker birthdayField;
     @FXML private Label errMsgLabel;
     @FXML private ImageView ImageBox;
     
     private File imageFile;
     
     /*
     this will return to previous 
     */
     public void backButoonPushed(ActionEvent push) throws IOException
     {
         Scenechanger sc = new Scenechanger();
         sc.scenechange(push, "contactTable.fxml", "Contact");
     }
     /*
      this will get information from SCENE BUILDER
     */
     public void saveButton(ActionEvent push)
     {
         try
         {
              contacts contacts;
             contacts = new contacts(firstNameTextField.getText(),lastNameTextField.getText(),phoneNumberTextField.getText(),addressTextField.getText(), birthdayField.getValue());
              
              contacts.insertIntoDB();
              
              Scenechanger sc = new Scenechanger();
              sc.scenechange(push,"contactTable.fxml", "Contact Table");
        }
         
         catch (Exception e)
         {
              System.err.println(e.getMessage());
         }
     }
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
       try
    {
        imageFile = new File("./src/images/defaultperson.png");
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageBox.setImage(image);
    } 
    
    catch(IOException e)
    {
        System.err.println(e.getMessage());
    }
    }    
    
    //default image 
    
    
}
