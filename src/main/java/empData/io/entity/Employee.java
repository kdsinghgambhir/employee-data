package empData.io.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity

public class Employee
{
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;

    public Employee()
    {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "id='" +id+ '\'' +
                ", firstName='" +firstname+ '\'' +
                ", lastName='" +lastname+ '\'' +
                ",email='" +email+ '\'' +
                '}';
    }
}