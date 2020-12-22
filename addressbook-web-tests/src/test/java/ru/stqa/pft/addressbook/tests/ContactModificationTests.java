package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Name").withMiddleName("MiddleName")
                    .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                    .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job").withFax("fax")
                    .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                    .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                    .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                    .withHome2("home2").withNotes("notes").withGroup("test1"),
                    "C:\\Java lessons\\addressbook-web-tests\\src\\test\\java\\ru\\stqa\\pft\\addressbook\\Элена и Вела.jpg");
        }
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test1").withMiddleName("Modified")
                .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job").withFax("fax")
                .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                .withHome2("home2").withNotes("notes").withGroup("test1");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));

//        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
