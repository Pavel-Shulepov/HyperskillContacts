package contacts.domains;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {

    private List<BaseContact> contactList;

    public PhoneBook() {
        contactList = new LinkedList<>();
    }

    public PhoneBook(List<BaseContact> contactList) {
        this.contactList = contactList;
    }

    public List<BaseContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<BaseContact> contactList) {
        this.contactList = contactList;
    }

    public boolean add(BaseContact contact) {
        return contactList.add(contact);
    }

    public boolean remove(BaseContact person) {
        return contactList.remove(person);
    }

    public BaseContact remove(int index) {
        return contactList.remove(index);
    }

    public BaseContact get(int index) {
        return contactList.get(index);
    }

    public BaseContact get(BaseContact baseContact) {
        return contactList.get(contactList.lastIndexOf(baseContact));
    }

    public List<BaseContact> search(String pattern) {
        return contactList
                .stream()
                .filter(baseContact -> {
                    var str = baseContact.toString().toLowerCase();
                    return str.toLowerCase().contains(pattern.toLowerCase());
                })
                .collect(Collectors.toList());
    }
}
