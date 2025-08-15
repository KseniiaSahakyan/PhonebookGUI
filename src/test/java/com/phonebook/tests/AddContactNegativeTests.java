package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactNegativeTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("pochtaks@gmail.com")
                .setPassword("Pochtaks$123"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactWithInvalidPhoneTest() {
        //click on 'ADD' link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact()
                .setName("Oliver")
                .setLastName("Adam")
                .setPhone("123")
                .setEmail("pochta@gm.com")
                .setAddress("Berlin")
                .setDescription("goalkeeper"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isAlertPresent());
    }

    @Test
    public void addContactWithInvalidEmailTest() {
        //click on 'ADD' link
        app.getContact().clickOnAddLink();
        //enter name
        app.getContact().fillContactForm(new Contact()
                .setName("Oliver")
                .setLastName("Adam")
                .setPhone("1234567890")
                .setEmail("pochtagm.com")
                .setAddress("Berlin")
                .setDescription("goalkeeper"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isAlertPresent());
    }
}
