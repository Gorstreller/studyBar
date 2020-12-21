package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhone() {
        app.goTo().gotoHomePage();
        app.goTo().gotoContactPage();
        app.contact().fillContactForm( new ContactData( "Test3", "Test2", "Test3",
                        "Test4", "Test", "Test", "Test", "home", "mobile",
                        "job", "fax", "email", "email2", "email3", "page",
                        "11", "March", "1997", "20", "November",
                        "1997", "address2", "home2", "notes", "test1"), true);
    }
}
