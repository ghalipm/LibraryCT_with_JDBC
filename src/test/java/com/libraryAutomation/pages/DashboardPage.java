package com.libraryAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//a[@href='#users']")
    public WebElement usersModuleTab;

    @FindBy(xpath = "//span[@class='title']")
    public List<WebElement> librarianModules;

    @FindBy(xpath = "(//h2)[1]")
    public WebElement userNumbersWE;

    @FindBy(xpath = "(//h2)[2]")
    public WebElement bookNumbersWE;

    @FindBy(xpath = "(//h2)[3]")
    public WebElement borrowedBookNumbersWE;

    @FindBy(xpath = "//h6")
    public List<WebElement> itemNamesWE;

    public int getUsersNumber() {
        String userNumbersText = userNumbersWE.getText();
        int userNumbersInt;
        userNumbersInt = Integer.parseInt(userNumbersText);
        return userNumbersInt;
    }

    public int getBooksNumber() {
        String bookNumbersText = bookNumbersWE.getText();
        int bookNumbersInt;
        bookNumbersInt = Integer.parseInt(bookNumbersText);
        return bookNumbersInt;
    }

    public int getBorrowedBooksNumber() {
        String borrowedBookNumbersText = borrowedBookNumbersWE.getText();
        int borrowedBookNumbersInt;
        borrowedBookNumbersInt = Integer.parseInt(borrowedBookNumbersText);
        return borrowedBookNumbersInt;
    }


    public List<String> getItemNames() {
        List<String> itemNamesText = new ArrayList<>();
        for (int i = 0; i < itemNamesWE.size(); i++) {
            itemNamesText.add(itemNamesWE.get(i).getText());
        }

        return itemNamesText;
    }

}
