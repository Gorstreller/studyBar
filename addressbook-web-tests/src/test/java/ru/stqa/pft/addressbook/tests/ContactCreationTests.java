package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Dates;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().gotoContactPage();
        ContactData contact = new ContactData( "Test3", "Test2", "Test3",
                "Test4", "Test", "Test", "Test", "home", "mobile", "job",
                "fax", "email", "email2", "email3", "page", "address2",
                "home2", "notes", "test1");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().loadPhoto(By.name("photo"),
                "C:\\Java lessons\\addressbook-web-tests\\src\\test\\java\\ru\\stqa\\pft\\addressbook\\Элена и Вела.jpg");
        app.getContactHelper().selectDates(new Dates("1997", "1997"), By.name("bday"),
                By.name("bmonth"), By.name("aday"), By.name("amonth"), "11");
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
