package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod

    @Test
    public void testContactModification() {
        Contacts before = app.dbHelper().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/Элена и Вела.jpg");
        if (app.contact().all().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("Name").withMiddleName("MiddleName")
                    .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                    .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job")
                    .withFax("fax").withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                    .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                    .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                    .withHome2("home2").withNotes("notes").withGroup("test1").withPhoto(photo));
        }
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test1")
                .withMiddleName("Modified").withLastName("LastName").withNickName("NickName").withTitle("title")
                .withCompany("company").withAddress("address1").withHomePhone("home").withMobilePhone("mobile")
                .withWorkPhone("job").withFax("fax").withEmail("email1").withEmail2("email2").withEmail3("email3")
                .withHomepage("page").withBirthDay("11").withBirthMonth("March").withBirthYear("1997")
                .withAnniversaryDay("20").withAnniversaryMonth("November").withAnniversaryYear("1997")
                .withAddress2("address2").withHome2("home2").withNotes("notes").withGroup("test1").withPhoto(photo);
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.dbHelper().contacts();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
