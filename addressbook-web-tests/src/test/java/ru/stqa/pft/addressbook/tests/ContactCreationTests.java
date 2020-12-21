package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().getContactList();
        app.goTo().gotoContactPage();
        ContactData contact = new ContactData( "Test3", "Test2", "Test3",
                "Test4", "Test", "Test", "Test", "home", "mobile", "job",
                "fax", "email", "email2", "email3", "page", "11",
                "March", "1997", "20", "November", "1997",
                "address2", "home2", "notes", "test1");
        app.contact().fillContactForm(contact, true);
        app.contact().loadPhoto(By.name("photo"),
                "C:\\Java lessons\\addressbook-web-tests\\src\\test\\java\\ru\\stqa\\pft\\addressbook\\Элена и Вела.jpg");
        app.contact().submitContactCreation();
        app.contact().returnToHomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
