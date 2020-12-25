package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

//    @BeforeMethod
//    public void ensurePreconditions(ContactData contact) {
//
//    }

    @Test(dataProvider = "validContacts")
    public void testContactModification(ContactData contact) {
        if (app.dbHelper().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(contact);
        }
        Contacts before = app.dbHelper().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/Элена и Вела.jpg");
        contact = new ContactData().withId(modifiedContact.getId()).withFirstName("test1")
                .withMiddleName("Modified").withLastName("LastName").withNickName("NickName").withTitle("title")
                .withCompany("company").withAddress("address1").withHomePhone("home").withMobilePhone("mobile")
                .withWorkPhone("job").withFax("fax").withEmail("email1").withEmail2("email2").withEmail3("email3")
                .withHomepage("page").withBirthDay("11").withBirthMonth("March").withBirthYear("1997")
                .withAnniversaryDay("20").withAnniversaryMonth("November").withAnniversaryYear("1997")
                .withAddress2("address2").withHome2("home2").withNotes("notes");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.dbHelper().contacts();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListUI();
    }

}
