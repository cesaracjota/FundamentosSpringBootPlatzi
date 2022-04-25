package com.fundamentosplatzi.fundamentos.entity;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private Long id;
    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne
    private User user;
//Constructor empty
    public Posts() {
    }

//Constructor with data
    public Posts(String description, User user) {
        this.description = description;
        this.user = user;
    }

    //Get and Set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//ToString
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
