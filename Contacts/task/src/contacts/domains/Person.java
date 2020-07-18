package contacts.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person extends BaseContact implements Serializable {

    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    public Person() {
        super();
    }

    public Person(String name, String surname, String number, LocalDate birthDate, Gender gender) {
        super();
        this.name = name;
        this.surname = surname;
        if (checkNumber(number)) this.number = number;
        else System.out.println("Wrong number format!");
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public List<String> getProperties() {
        ArrayList<String> list = new ArrayList<>(super.getProperties());
        list.add("surname");
        list.add("birthDate");
        list.add("gender");
        return list;
    }

    @Override
    public void setProperty(String property, Object value) {
        switch (property.toLowerCase()) {
            case "surname":
                surname = (String) value;
                break;
            case "birthDate":
                birthDate = (LocalDate) value;
                break;
            case "gender":
                gender = Enum.valueOf(Gender.class, (String) value);
                break;
            default:
                super.setProperty(property, value);
                break;
        }
    }

    @Override
    public Object getProperty(String property) {
        switch (property.toLowerCase()) {
            case "surname":
                return surname;
            case "birthDate":
                return birthDate;
            case "gender":
                return gender;
            default:
                return super.getProperty(property);
        }
    }

    @Override
    public String getName() {
        return super.getName() + " " + surname;
    }

    @Override
    public String toString() {
        return super.toString() + " " + surname + " " + birthDate + " " + gender;
    }

    @Override
    public void printInfo() {
        System.out.printf("Name: %s\n" +
                        "Surname: %s\n" +
                        "Birth date: %s\n" +
                        "Gender: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n",
                name,
                surname,
                birthDate == null ? "[no data]" : birthDate,
                gender == null ? "[no data]" : gender,
                number,
                created,
                edit);
    }
}
