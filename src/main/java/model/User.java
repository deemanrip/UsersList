package model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "isAdmin", columnDefinition = "boolean default false", nullable = false)
    private boolean admin = false;

    @Column(name = "createdDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", age: " + age + ", isAdmin: " + admin
                + ", createdDate: " + createdDate;
    }
}