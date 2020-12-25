package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactEditionById(contact);
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    private void initContactEditionById(ContactData modifiedContact) {
        int id = modifiedContact.getId();
        click(By.xpath("//td/a[@href='edit.php?id=" + id + "']/img"));
    }

    private void initContactModificationById(int id) {
        click(By.xpath("//td/a[@href='edit.php?id=" + id + "']/img"));
    }

    public void delete(ContactData contact) {
        initContactDeletionById(contact.getId());
        submitContactDeletion();
        submitAlert(5);
        contactCache = null;
        returnToHomePage();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        String birthDay = contactData.getBirthDay();

        if (birthDay == null) {
            birthDay = "-";
        }
        setDate(By.name("bday"), birthDay);

        String birthMonth = contactData.getBirthMonth();
        if (birthMonth == null) {
            birthMonth = "-";
        }
        setDate(By.name("bmonth"), birthMonth);
        type(By.name("byear"), contactData.getBirthYear());

        String anniversaryDay = contactData.getAnniversaryDay();
        if (anniversaryDay == null) {
            anniversaryDay = "-";
        }
        setDate(By.name("aday"), anniversaryDay);

        String anniversaryMonth = contactData.getAnniversaryMonth();
        if (anniversaryMonth == null) {
            anniversaryMonth = "-";
        }
        setDate(By.name("amonth"), anniversaryMonth);
        type(By.name("ayear"), contactData.getAnniversaryYear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getHome2());
        type(By.name("notes"), contactData.getNotes());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(driver.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitContactCreation() {
        click(By.name("submit"));
    }

    private void setDate(By locator, String bday) {
        new Select(driver.findElement(locator)).selectByVisibleText(bday);
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void initContactEdition(int index) {
        driver.findElements(By.cssSelector("a > img[title = 'Edit']")).get(index).click();
    }

    public void initContactDeletionById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactDeletion() {
        click(By.cssSelector("div.left > input[value = 'Delete']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitAlert(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("a > img[title = 'Edit']"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        Set<ContactData> contacts = new HashSet<>();
//        if (contactCache != null) {
//            return new Contacts(contactCache);
//        }
//        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
            .withAllPhones(allPhones));
//            contactCache.add(new ContactData().withId(id).withFirstName(cells));
        }
        return new Contacts(contacts);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String home2 = driver.findElement(By.name("phone2")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHome2(home2);
    }
}
