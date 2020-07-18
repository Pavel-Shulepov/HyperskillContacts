package contacts.domains;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseContact implements IBaseContact {
    protected String number = "[no number]";
    protected String name;
    protected LocalDateTime created;
    protected LocalDateTime edit;

    public BaseContact() {
        this.created = LocalDateTime.now();
        this.edit = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getEdit() {
        return edit;
    }

    public void setEdit(LocalDateTime edit) {
        this.edit = edit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (checkNumber(number)) this.number = number;
        else {
            this.number = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasNumber() {
        return !"[no number]".equals(number);
    }

    boolean checkNumber(String number) {
        String patterns = "\\+?((\\([0-9A-Za-z]+\\)|[0-9A-Za-z]+)|([0-9A-Za-z]+[ -]\\([0-9A-Za-z]{2,}\\))|[0-9A-Za-z]+[ -][0-9A-Za-z]{2,})"
                +"([ -][0-9A-Za-z]{2,}[ -]?)*";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public String toString() {
        return number + " " + name + " " + created.toString() + " " + edit.toString();
    }

    public void printInfo() {
    }

    public List<String> getProperties() {
        return List.of("number", "name", "created", "edit");
    }

    public void setProperty(String property, Object value) {
        switch (property.toLowerCase()) {
            case "number":
                number = (String) value;
                break;
            case "name":
                name = (String) value;
                break;
            case "created":
                created = (LocalDateTime) value;
                break;
            case "edit":
                edit = (LocalDateTime) value;
                break;
        }
    }

    public Object getProperty(String property) {
        switch (property.toLowerCase()) {
            case "number":
                return number;
            case "name":
                return name;
            case "created":
                return created;
            case "edit":
                return edit;
            default:
                throw new RuntimeException("Нет такого поля");
        }
    }

}
