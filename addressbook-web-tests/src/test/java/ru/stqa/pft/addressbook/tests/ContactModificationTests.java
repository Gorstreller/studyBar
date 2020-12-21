package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Dates;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().getContactList();
        if (! app.contact().isThereAContact()) {
            app.goTo().gotoContactPage();
            app.contact().createContact(new ContactData( "Test3", "Test2", "Test3",
                    "Test4", "Test", "Test", "Test", "home", "mobile", "job",
                    "fax", "email", "email2", "email3", "page", "11",
                    "March", "1997", "20", "November", "1997",
                    "address2", "home2", "notes", "test1"));
        }
        app.contact().initContactEdition(before.size() - 1);
        ContactData contact = new ContactData( "Test3", "Modified", "Test3",
                "Test4", "Test", "Test", "Test", "home", "mobile", "job",
                "fax", "email", "email2", "email3", "page", "11",
                "March", "1997", "20", "November", "1997",
                "address2", "home2", "notes", "test1");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.contact().returnToHomePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size());

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
