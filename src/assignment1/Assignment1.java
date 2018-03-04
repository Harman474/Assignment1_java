/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import models.contacts;

/**
 *
 * @author harma
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        contacts contacts = new contacts("hello", "hello", "705 970 7785","198 livingstone st E ", LocalDate.of(1998, Month.MAY, 22),
                new File("./src/images/goku.png"));
        System.out.printf("contact is: %s%n", contacts);

        contacts.insertIntoDB();
    }

}
