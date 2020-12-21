package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        if (! app.contact().isThereAContact()) {
            app.goTo().contactPage();
            String path = "C:\\Java lessons\\addressbook-web-tests\\src\\test\\java\\ru\\stqa\\pft\\addressbook\\Элена и Вела.jpg";
            app.contact().create(new ContactData().withFirstName("Name").withMiddleName("MiddleName")
                    .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                    .withAddress("address1").withHome("home").withMobile("mobile").withWork("job").withFax("fax")
                    .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                    .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                    .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                    .withHome2("home2").withNotes("notes").withGroup("test1"), path);
        }
        app.contact().delete(deletedContact);

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
