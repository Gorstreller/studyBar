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

    public void modify(ContactData contact) {
        initContactEditById(contact);
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    private void initContactEditById(ContactData modifiedContact) {
        int id = modifiedContact.getId();
        click(By.xpath("//td/a[@href='edit.php?id=" + id + "']" +
                "/img[@title = 'Edit']"));
//        driver.findElement(By.cssSelector("input[value='" + id + "']"))
//                .findElement(By.xpath("td::tr[name()='entry']"))
//                .findElement(By.xpath("td[8]")).click();

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

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
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
            int id = Integer.parseInt(element.findElement(By.cssSelector("td.center > input[name='selected[]']"))
                    .getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstName));
        }
        return new Contacts(contactCache);
    }

}
