package dataUser;

public class DataUser {

    private int id;
    private String name;
    private String surname;
    private String personId;
    private String uuid;

    public DataUser() {
    }

    public DataUser(int id, String name, String surname, String personid, String uuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personId = personid;
        this.uuid = uuid;
    }

    public DataUser(String name, String surname, String personid, String uuid) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
        this.personId = personid;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
