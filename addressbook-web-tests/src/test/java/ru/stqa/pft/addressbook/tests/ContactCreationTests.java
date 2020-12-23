package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<>();
        File photo = new File("src/test/resources/Элена и Вела.jpg");
        list.add(new Object[] {new ContactData().withFirstName("Name1").withMiddleName("Mid1").withLastName("Last1")
                .withPhoto(photo).withGroup("test1")});
        list.add(new Object[] {new ContactData().withFirstName("Name2").withMiddleName("Mid2").withLastName("Last2")
                .withPhoto(photo).withGroup("test2")});
        list.add(new Object[] {new ContactData().withFirstName("Name3").withMiddleName("Mid3").withLastName("Last3")
                .withPhoto(photo).withGroup("test3")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
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
        File photo = new File("src/test/resources/Элена и Вела.jpg");
        ContactData contact = new ContactData().withFirstName("Name'").withMiddleName("MiddleName'")
                .withLastName("LastName'").withNickName("NickName").withTitle("title").withCompany("company")
                .withAddress("address1").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("job")
                .withFax("fax").withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
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
