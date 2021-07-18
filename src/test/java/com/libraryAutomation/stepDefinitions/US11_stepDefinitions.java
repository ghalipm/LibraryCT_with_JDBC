package com.libraryAutomation.stepDefinitions;

import com.libraryAutomation.JDBC_Tests.Library1_DB;
import com.libraryAutomation.JDBC_Tests.Library1_DB.*;
import com.libraryAutomation.pages.DashboardPage;
import com.libraryAutomation.utilities.ConfigurationReader;
import com.libraryAutomation.utilities.DB_Utility;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class US11_stepDefinitions {

    DashboardPage dashboardPage=new DashboardPage();

    public int bookCountDB;
    public int userCountDB;
    public int borrowedBookCountDB;

    @Then("the user should see that the numbers from DB and UI matches for:")
    public void the_user_should_see_that_the_numbers_from_db_and_ui_matches_for(List<String> expectedItemNames) {
        // Write code here that turns the phrase above into concrete actions
        int usersNumberUI=dashboardPage.getUsersNumber();
        int booksNumberUI=dashboardPage.getBooksNumber();
        int borrowedBooksUI=dashboardPage.getBorrowedBooksNumber();

        String url      = com.libraryAutomation.utilities.ConfigurationReader.getProperty("library1.database.url");
        String username = ConfigurationReader.getProperty("library1.database.username");
        String password = ConfigurationReader.getProperty("library1.database.password");

        DB_Utility.createConnection(url, username, password);
        DB_Utility.runQuery("SELECT count(*) FROM books");

        String bookCountDB_Str =  DB_Utility.getFirstRowFirstColumn();
        bookCountDB = Integer.parseInt(bookCountDB_Str);
        System.out.print("bookCountDB = " + bookCountDB);
        System.out.println("\t bookCountUI = " + booksNumberUI);

        DB_Utility.runQuery("SELECT count(*) FROM users")  ;
        String userCountDB_Str =  DB_Utility.getCellValue(1,1);
        userCountDB = Integer.parseInt(userCountDB_Str) ;
        System.out.print("userCountDB = " + userCountDB);
        System.out.println("\t userCountUI = " + usersNumberUI);

        DB_Utility.runQuery("SELECT count(*) FROM book_borrow")  ;
        String borrowedBookCountDB_Str =  DB_Utility.getCellValue(1,1);
        borrowedBookCountDB = Integer.parseInt(borrowedBookCountDB_Str) ;
        System.out.print("borrowedBookCountDB = " + borrowedBookCountDB);
        System.out.println("\t borrowedBookCountUI = " + borrowedBooksUI);

        System.out.println(" borrowedBookCountDB-borrowedBooksUI = " + (borrowedBookCountDB - borrowedBooksUI));

        List<String> actual_UI_Names=dashboardPage.getItemNames();
        assertEquals("Item names did not match" ,expectedItemNames, actual_UI_Names);
        assertEquals("Book count does not match!", bookCountDB,booksNumberUI );
        assertEquals("User count does not match!", userCountDB, usersNumberUI );
        assertNotEquals("Borrowed book counts  match!", borrowedBookCountDB-borrowedBooksUI==0);


    }


}
