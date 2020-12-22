package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/java/ru/stqa/pft/addressbook/Элена и Вела.jpg");
        ContactData contact = new ContactData().withFirstName("Name").withMiddleName("MiddleName")
                .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job").withFax("fax")
                .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                .withHome2("home2").withNotes("notes").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

//        assertThat(after, equalTo(before.withAdded(
//                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testContactBadCreation() {
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/java/ru/stqa/pft/addressbook/Элена и Вела.jpg");
        ContactData contact = new ContactData().withFirstName("Name'").withMiddleName("MiddleName'")
                .withLastName("LastName'").withNickName("NickName").withTitle("title").withCompany("company")
                .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job").withFax("fax")
                .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                .withHome2("home2").withNotes("notes").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Set<ContactData> after = app.contact().all();

        assertThat(after, equalTo(before));
    }
}
