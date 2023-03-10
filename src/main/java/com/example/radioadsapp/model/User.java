package com.example.radioadsapp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String token;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;


    @Column(columnDefinition = "boolean default true")
    private boolean newEntry;

    private String photo;



    public User() {

    }

    public User(String firstName, String lastName, String email, String password, Collection<Role> roles, boolean newEntry, String photo) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.newEntry = newEntry;
        this.photo = photo;
    }



    public boolean isNewEntry() {
        return newEntry;
    }

    public void setNewEntry(boolean newEntry) {
        this.newEntry = newEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public String getPhotoPath() {
        if (photo == null || id == null) return null;

        return "/profile-photos/" + id + "/" + photo;
    }

    @Transient
    public String getName() {
        return firstName + ' ' + lastName;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +

                ", newEntry=" + newEntry +
                ", photo='" + photo + '\'' +
                '}';
    }
}
