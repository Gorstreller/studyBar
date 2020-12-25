package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public static void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] p) {
        logger.info("Start test " + method.getName() + " with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        logger.info("Stop test " + method.getName());
    }

    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.dbHelper().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData()
                            .withId(g.getId())
                            .withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    protected void verifyContactListUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.dbHelper().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData()
                            .withFirstName(g.getFirstName())
                            .withMiddleName(g.getMiddleName())
                            .withLastName(g.getLastName())
                            .withNickName(g.getNickName())
                            .withTitle(g.getTitle())
                            .withCompany(g.getCompany())
                            .withAddress(g.getAddress())
                            .withHomePhone(g.getHomePhone())
                            .withMobilePhone(g.getMobilePhone())
                            .withWorkPhone(g.getWorkPhone())
                            .withFax(g.getFax())
                            .withEmail(g.getEmail())
                            .withEmail2(g.getEmail2())
                            .withEmail3(g.getEmail3())
                            .withHomepage(g.getHomepage())
                            .withBirthDay(g.getBirthDay())
                            .withBirthMonth(g.getBirthMonth())
                            .withBirthYear(g.getBirthYear())
                            .withAnniversaryDay(g.getAnniversaryDay())
                            .withAnniversaryMonth(g.getAnniversaryMonth())
                            .withAnniversaryYear(g.getAnniversaryYear())
                            .withAddress2(g.getAddress2())
                            .withHome2(g.getHome2())
                            .withNotes(g.getNotes())
                            .withAllPhones(g.getAllPhones()))
                    .collect(Collectors.toSet())));
        }
    }
}
