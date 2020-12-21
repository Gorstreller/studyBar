package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withFirstName("Name").withMiddleName("MiddleName")
                .withLastName("LastName").withNickName("NickName").withTitle("title").withCompany("company")
                .withAddress("address1").withHome("home").withMobile("mobile").withWork("job").withFax("fax")
                .withEmail("email1").withEmail2("email2").withEmail3("email3").withHomepage("page")
                .withBirthDay("11").withBirthMonth("March").withBirthYear("1997").withAnniversaryDay("20")
                .withAnniversaryMonth("November").withAnniversaryYear("1997").withAddress2("address2")
                .withHome2("home2").withNotes("notes").withGroup("test1");
        String path = "C:\\Java lessons\\addressbook-web-tests\\src\\test\\java\\ru\\stqa\\pft\\addressbook\\Элена и Вела.jpg";
        app.contact().create(contact, path);
        app.contact().returnToHomePage();
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
//        assertThat(after, equalTo(before.withAdded(contact.withId())));
    }

}
