package frms.localsqlapp.model;

/**
 * Created by Formation on 10/01/2018.
 */

public class Contact {
    private String name;
    private String surname;
    private String email;
    private  long id;

    public Contact() {
    }

    public Contact(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
