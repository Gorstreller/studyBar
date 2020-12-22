package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
                    .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job").withFax("fax")
                    .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                    .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                    .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                    .withHome2("home2").withNotes("notes").withGroup("test1"), path);
        }
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Set<ContactData> after = app.contact().all();
//        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
