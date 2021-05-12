package com.libraryAutomation.JDBC_Tests;

import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.DB_Utility;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Library1_DB {

    /**
     * Assuming that we captured the UI dashboard numbers from library1 app
     * Now we want to makes sure those numbers match the database data
     */

    public int bookCountDB;
    public int userCountDB;
    public int borrowedBookCountDB;

    @Test
    public void testDashboardNumbers(){

        int userCountUI = 9324 ;
        int bookCountUI = 3663 ;
        int borrowedBookCountUI = 805 ;

        String url      = com.libraryAutomation.utilities.ConfigurationReader.getProperty("library1.database.url");
        String username = ConfigurationReader.getProperty("library1.database.username");
        String password = ConfigurationReader.getProperty("library1.database.password");

        DB_Utility.createConnection(url, username, password);
        DB_Utility.runQuery("SELECT count(*) FROM books");

        String bookCountDB_Str =  DB_Utility.getFirstRowFirstColumn();
        bookCountDB = Integer.parseInt(bookCountDB_Str);
        System.out.println("bookCountDB = " + bookCountDB);

        DB_Utility.runQuery("SELECT count(*) FROM users")  ;
        String userCountDB_Str =  DB_Utility.getCellValue(1,1);
        userCountDB = Integer.parseInt(userCountDB_Str) ;
        System.out.println("userCountDB = " + userCountDB);

        DB_Utility.runQuery("SELECT count(*) FROM book_borrow")  ;
        String borrowedBookCountDB_Str =  DB_Utility.getCellValue(1,1);
        borrowedBookCountDB = Integer.parseInt(borrowedBookCountDB_Str) ;
        System.out.println("borrowedBookCountDB = " + borrowedBookCountDB);

        assertEquals("Book count does not match!", bookCountDB, bookCountUI );
        assertEquals("Book count does not match!", bookCountUI, bookCountDB );
        assertEquals("User count does not match!", userCountDB, userCountUI );
        assertEquals("Borrowed book count does not match!", borrowedBookCountDB, borrowedBookCountUI);

        System.out.println("borrowedBookCountUI = " + borrowedBookCountUI);
        System.out.println("borrowedBookCountDB = " + borrowedBookCountDB);

        DB_Utility.destroy();

    }


}