package contacts.services;

import contacts.domains.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactService {

    private final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook = new PhoneBook();

    public ContactService() {
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void remove() {
        list();
        System.out.print("Select a record: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index < 0 || index > phoneBook.getContactList().size()) return;
        phoneBook.remove(index - 1);
        System.out.print("The record removed!\n");
    }

    public void edit(int index) {
        Objects.checkIndex(index - 1, phoneBook.getContactList().size());
        BaseContact contact = phoneBook.get(index - 1);
        var properties = contact.getProperties();
        StringBuilder props = new StringBuilder();
        for (String property : properties) {
            props.append(", ").append(property);
        }
        System.out.printf("Select a field (%s): ", props.toString());
        String field = scanner.nextLine();
        System.out.printf("Enter %s: ", field);
        contact.setProperty(field, scanner.nextLine());
        System.out.println("Saved");
    }


    public void list() {
        int i = 1;
        for (IBaseContact contact: phoneBook.getContactList()) {
            System.out.printf("%s. %s \n", i++, contact.toString());
        }
        System.out.print("\n[list] Enter action ([number], back): ");
        String action = scanner.nextLine();
        int index = 0;
        if (isNumeric(action)) {
            index = Integer.parseInt(action);
            Objects.checkIndex(index - 1, phoneBook.getContactList().size());
            if (index < 0 || index > phoneBook.getContactList().size()) return;
            BaseContact contact = phoneBook.get(index - 1);
            contact.printInfo();
        }
        record(index);
    }

    private void record(int index) {
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        var action = scanner.nextLine();
        switch (action.toLowerCase()) {
            case "edit":
                edit(index);
                break;
            case "delete":
                break;
            case "menu":
                break;
        }
    }

    public boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public void add() {
        System.out.print("\nEnter the type (person, organization): ");
        String type = scanner.nextLine();
        if ("person".equals(type.toLowerCase())) addPerson();
        else if ("organization".equals(type.toLowerCase())) addOrganization();
    }

    private void addPerson() {
        Person person = new Person();
        System.out.print("Enter the name: ");
        person.setName(scanner.nextLine());
        System.out.print("Enter the surname: ");
        person.setSurname(scanner.nextLine());
        System.out.print("Enter the birth date: ");
        try {
            person.setBirthDate(LocalDate.parse(scanner.nextLine()));
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
        }

        System.out.print("Enter the gender (M, F): ");

        try {
            person.setGender(Gender.valueOf(scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("Bad gender!");
        }

        System.out.print("Enter the number: ");
        person.setNumber(scanner.nextLine());
        phoneBook.add(person);
        System.out.println("The record added.");
    }

    private void addOrganization() {
        Organization organization = new Organization();
        System.out.print("Enter the organization name: ");
        organization.setName(scanner.nextLine());
        System.out.print("Enter the address: ");
        organization.setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        organization.setNumber(scanner.nextLine());
        phoneBook.add(organization);
        System.out.println("The record added.");
    }

    public void search() {
        System.out.print("Enter search query: ");
        var pattern = scanner.nextLine();
        var list = phoneBook.search(pattern);
        System.out.printf("Found %s results:\n", list.size());
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%s. %s \n", i, list.get(i - 1).getName());
        }
        System.out.print("\n[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        if (isNumeric(action)) {
            int index = Integer.parseInt(action);
            Objects.checkIndex(index - 1, list.size());
            list.get(index - 1).printInfo();
            record(index);
        }
        switch (action.toLowerCase()) {
            case "back":
                break;
            case "again":
                search();
                break;
        }
    }
}
