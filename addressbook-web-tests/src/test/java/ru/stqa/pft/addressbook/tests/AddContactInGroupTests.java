package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class AddContactInGroupTests extends TestBase{

    @Test
    public void testAddContactInGroup() {
        Contacts before = app.dbHelper().contacts();
        ContactData contactForAddingGroup = before.iterator().next();
        app.contact().setSelector(By.name("group"), "[all]");
        app.contact().addContactInGroup(contactForAddingGroup, "test 0");
        app.goTo().gotoHomePage();
    }
}
