package org.data;

public class UserData {
    public final String name;
    public final String surname;
    public final int dayOfBirth;
    public final int monthOfBirth;
    public final int yearOfBirth;

    public UserData(String name, String surname, int day, int month, int year) {
        this.name = name;
        this.surname = surname;
        this.dayOfBirth = day;
        this.monthOfBirth = month;
        this.yearOfBirth = year;
    }
}
