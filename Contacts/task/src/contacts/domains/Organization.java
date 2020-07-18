package contacts.domains;

import java.io.Serializable;
import java.util.*;

public class Organization extends BaseContact implements Serializable {
    private String address;

    public Organization() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + " " + address;
    }

    @Override
    public void printInfo() {
        System.out.printf("Organization name: %s\n" +
                        "Address: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n",
                name,
                address,
                number,
                created,
                edit);
    }

    @Override
    public List<String> getProperties() {
        ArrayList<String> list = new ArrayList<>(super.getProperties());
        list.add("address");
        return list;
    }

    @Override
    public void setProperty(String property, Object value) {
        if ("address".equals(property.toLowerCase())) {
            address = (String) value;
        } else {
            super.setProperty(property, value);
        }
    }

    @Override
    public Object getProperty(String property) {
        if ("address".equals(property.toLowerCase())) {
            return address;
        } else {
            return super.getProperty(property);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
