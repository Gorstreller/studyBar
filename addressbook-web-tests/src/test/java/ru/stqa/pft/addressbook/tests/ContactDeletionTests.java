package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Dates;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test(enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()) {
            app.goTo().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Test2", "Test2", "Test3",
                    "Test4", "Test", "Test", "Test", "home", "mobile", "job",
                    "fax", "email", "email2", "email3", "page", "address2",
                    "home2", "notes", "test1"), new Dates("1997", "1997"));
        }
        app.getContactHelper().initContactEdition(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
