package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ContactDataGeneratorDiana {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data Format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGeneratorDiana generator = new ContactDataGeneratorDiana();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(format("firstname %s", i))
                    .withMiddleName(format("middlename %s", i))
                    .withLastName(format("lastname %s", i))
                    .withNickName(format("nickname %s", i))
                    .withPhoto(new File("src/test/resources/1.jpg"))
                    .withTitle(format("title %s", i))
                    .withCompany(format("company %s", i))
                    .withAddress(format("address %s", i))
                    .withHomePhone(format("home phone %s", i))
                    .withMobilePhone(format("mobile phone %s", i))
                    .withWorkPhone(format("workphone %s", i))
                    .withFax(format("fax %s", i))
                    .withEmail(format("email1@test%s.com", i))
                    .withEmail2(format("email2@test%s.com", i))
                    .withEmail3(format("email3@test%s.com", i))
                    .withBirthDay("1")
                    .withBirthMonth("January")
                    .withBirthYear("2000")
                    .withAnniversaryDay("1")
                    .withAnniversaryMonth("January")
                    .withAnniversaryYear("2000")
                    .withHomepage(format("homepage %s", i))
                    .withAddress2(format("address %s", i))
                    .withHome2(format("optional phone %s", i))
                    .withNotes(format("notes %s", i)));
        }
        return contacts;
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;" +
                                "%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstName(),
                        contact.getMiddleName(),
                        contact.getLastName(),
                        contact.getNickName(),
                        contact.getPhoto(),
                        contact.getTitle(),
                        contact.getCompany(),
                        contact.getAddress(),
                        contact.getHomePhone(),
                        contact.getMobilePhone(),
                        contact.getWorkPhone(),
                        contact.getFax(),
                        contact.getEmail(),
                        contact.getEmail2(),
                        contact.getEmail3(),
                        contact.getBirthDay(),
                        contact.getBirthMonth(),
                        contact.getBirthYear(),
                        contact.getAnniversaryDay(),
                        contact.getAnniversaryMonth(),
                        contact.getAnniversaryYear(),
                        contact.getGroups(),
                        contact.getHomepage(),
                        contact.getAddress2(),
                        contact.getHome2(),
                        contact.getNotes()));
            }
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().
                excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        switch (format) {
            case "csv":
                saveAsCsv(contacts, new File(file));
                break;
            case "xml":
                saveAsXml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts, new File(file));
                break;
            default:
                System.out.println("Unrecognized format " + format);
                break;
        }
    }
}
