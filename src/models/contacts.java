/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author harma
 */
public class contacts {
    private String  firstName, lastName, phoneNumber, address;
    private LocalDate birthday;
    private File imageFile;
    private int contactID;

    public contacts(String firstName, String lastName, String phoneNumber,String address, LocalDate birthday) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setBirthday(birthday);
        setImageFile(new File("./src/images/defaultPerson.png" ));
    }

    public contacts(String firstName, String lastName, String phoneNumber, String address, LocalDate birthday, File image) {
        this(firstName,lastName,phoneNumber, address,birthday);
        setImageFile(imageFile);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    } 
    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File image) {
        this.imageFile = image;
        
    }

    public int getcontactID() {
        return contactID;
    }

    public void setcontactID(int ID) {
        if(contactID >= 0)
        this.contactID = ID;
        else
            throw new IllegalArgumentException("ID must be equal or Greater to 0");
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%s %s %s is %d years old", firstName, lastName,address, Period.between(birthday, LocalDate.now()).getYears());
}

    
    /*
    this method will input contacts to database
    */
    
    public void insertIntoDB () throws SQLException
    {
       Connection conn = null; 
       PreparedStatement ps = null;
       try {
           
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://LocalHost:3306/contacts","root", "");
           String sql = "INSERT into contacts(firstName, lastName, phoneNumber,address, birthday,imageFile)"
                   + "Values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
            Date dob = Date.valueOf(birthday);
            // bind the parameters
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phoneNumber);
            ps.setString(4,address);
            ps.setDate(5, dob);
            ps.setString(6, imageFile.getName());
            
            ps.execute();
       }
       catch (Exception e)
       {
        System.err.println(e.getMessage());
       }
        finally
       {
           if 
               (conn != null)
               conn.close();
           if 
               (ps != null)
               ps.close();
       }
       
    }
    
}
