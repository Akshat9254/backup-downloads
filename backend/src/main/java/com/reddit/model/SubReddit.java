package com.reddit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
public class SubReddit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
    @ManyToOne
    private User admin;
    @ManyToMany(mappedBy = "joinedCommunities")
    private Set<User> participants = new HashSet<>();
    @OneToMany(mappedBy = "subReddit")
    private Set<Post> posts = new HashSet<>();
}
