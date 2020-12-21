package ru.stqa.pft.addressbook.appmanager;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void create(ContactData contact, String pathToPhoto) {
        fillContactForm(contact, true);
        loadPhoto(By.name("photo"), pathToPhoto);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact, int index) {
        initContactEdition(index);
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(int index) {
        initContactEdition(index);
        submitContactDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        setDate(By.name("bday"), contactData.getBirthDay());
        setDate(By.name("bmonth"), contactData.getBirthMonth());
        type(By.name("byear"), contactData.getBirthYear());
        setDate(By.name("aday"), contactData.getAnniversaryDay());
        setDate(By.name("amonth"), contactData.getAnniversaryMonth());
        type(By.name("ayear"), contactData.getAnniversaryYear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getHome2());
        type(By.name("notes"), contactData.getNotes());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void loadPhoto(By locator, String path) {
        driver.findElement(locator).sendKeys(path);
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

    public void submitContactDeletion() {
        click(By.cssSelector("form[method = 'get'] > input[value = 'Delete']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("a > img[title = 'Edit']"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        String locator = "//tr[@name = 'entry']";
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        for (WebElement element : elements) {
            WebElement text = driver.findElement(By.xpath(locator + "//td[position() = 2]"));
            String firstName = text.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.cssSelector("tr[name = 'entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath("//td[position()=2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td[position()=2]")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstName));
        }
        return new Contacts(contactCache);
    }
}
