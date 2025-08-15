package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.MyDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
        //click on 'ADD' link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact()
                .setName("Oliver")
                .setLastName("Adam")
                .setPhone("1234567890")
                .setEmail("pochta@gm.com")
                .setAddress("Berlin")
                .setDescription("goalkeeper"));
        //click Save button
        app.getContact().clickOnSaveButton();
        //assert new Contact by string
        Assert.assertTrue(app.getContact().isContactCreated("Oliver"));
    }


    @Test(dataProvider = "addNewContact", dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderPositiveTest(String name, String lastName, String phone, String email, String address, String description) {
        //click on 'ADD' link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreated(name));
    }

    @Test(dataProvider = "addNewContactFromCsv", dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderWithCsvFilePositiveTest(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactCreatedByPhone(contact.getPhone()));
    }

    @AfterMethod (enabled = false)
    public void postCondition() {
        while (!app.getContact().isContactListEmpty()) {
            app.getContact().removeContact();
        }

    }

}
