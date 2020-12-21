package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;
    private final String address;
    private final String home;
    private final String mobile;
    private final String work;
    private final String fax;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homepage;
    private final String birthDay;
    private final String birthMonth;
    private final String birthYear;
    private final String anniversaryDay;
    private final String anniversaryMonth;
    private final String anniversaryYear;
    private final String address2;
    private final String home2;
    private final String notes;
    private String group;

    public ContactData(int id, String firstName, String middleName, String lastName, String nickName,
                       String title, String company, String address, String home, String mobile,
                       String work, String fax, String email, String email2, String email3,
                       String homepage, String birthDay, String birthMonth, String birthYear, String anniversaryDay,
                       String anniversaryMonth, String anniversaryYear, String address2, String home2, String notes, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.anniversaryDay = anniversaryDay;
        this.anniversaryMonth = anniversaryMonth;
        this.anniversaryYear = anniversaryYear;
        this.address2 = address2;
        this.home2 = home2;
        this.notes = notes;
        this.group = group;
    }

    public ContactData(String firstName, String middleName, String lastName, String nickName,
                       String title, String company, String address, String home, String mobile,
                       String work, String fax, String email, String email2, String email3,
                       String homepage, String birthDay, String birthMonth, String birthYear,
                       String anniversaryDay, String anniversaryMonth, String anniversaryYear,
                       String address2, String home2, String notes, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.anniversaryDay = anniversaryDay;
        this.anniversaryMonth = anniversaryMonth;
        this.anniversaryYear = anniversaryYear;
        this.address2 = address2;
        this.home2 = home2;
        this.notes = notes;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getAnniversaryDay() {
        return anniversaryDay;
    }

    public String getAnniversaryMonth() {
        return anniversaryMonth;
    }

    public String getAnniversaryYear() {
        return anniversaryYear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHome2() {
        return home2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName);
    }
}
