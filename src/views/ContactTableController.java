/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import assignment1.Assignment1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.contacts;

/**
 * FXML Controller class
 *
 * @author harma
 */
public class ContactTableController implements Initializable {
    
      @FXML private TableView<contacts> ContactTable;
      @FXML private TableColumn<contacts,Integer>contactID;
      @FXML private TableColumn<contacts,String>firstName;
      @FXML private TableColumn<contacts,String>lastName;
      @FXML private TableColumn<contacts,String>address;
      @FXML private TableColumn<contacts,String>phoneNumber;
      
      
      
      public void createNewContactButton (ActionEvent push) throws IOException
      {
          Scenechanger sc = new Scenechanger();
          sc.scenechange(push, "newcontact.fxml","Create New Contact");
      }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        contactID.setCellValueFactory(new PropertyValueFactory<contacts, Integer>("contactID"));
        firstName.setCellValueFactory(new PropertyValueFactory<contacts, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<contacts, String>("lastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<contacts, String>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<contacts, String>("address"));
        
        try 
        {
            loadcontacts();
        }
        catch(SQLException e)
        {
             System.err.println(e.getMessage());       
        }
    }    
    
    public void loadcontacts() throws SQLException
    {
        ObservableList<contacts> contacts = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
    
        try
        {
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://LocalHost:3306/contacts","root", "");
            
            st = conn.createStatement();
            
            rs = st.executeQuery("SELECT * FROM contacts");
            //create contacts object from  each record
            while (rs.next())
                 {
                contacts newcontacts = new contacts(rs.getString("firstName") , rs.getString("lastName"), rs.getString("phoneNumber"), rs.getString("address"),rs.getDate("birthday").toLocalDate());
                newcontacts.setcontactID(rs.getInt("contactID"));
                newcontacts.setImageFile(new File(rs.getString("imageFile")));
                
                contacts.add(newcontacts);
                }
            ContactTable.getItems().addAll(contacts);
        }
        
        catch( Exception e)
        {
             System.err.println(e.getMessage()); 
        }
             
        finally
        {
        if (conn != null)
                conn.close();
        if (st!= null)
                st.close();
        if (rs!= null)
            rs.close();
        }
        
    }
}
