package contacts;

import contacts.services.ContactService;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        ContactService contactService = new ContactService();
        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = scanner.nextLine();
            switch (action.toLowerCase()) {
                case "add":
                    contactService.add();
                    break;
                case "list":
                    contactService.list();
                    break;
                case "search":
                    contactService.search();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %s records.", contactService.getPhoneBook().getContactList().size());
                    break;
                case "exit":
                    return;
            }
        }

    }

}
