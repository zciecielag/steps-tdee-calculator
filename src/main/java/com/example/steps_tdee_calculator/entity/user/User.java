package com.example.steps_tdee_calculator.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String username;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String surname;
    @Column(nullable = false, length = 100)
    private String password;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.surname = builder.surname;
        this.password = builder.password;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserBuilder {
        private String username;
        private String name;
        private String surname;
        private String password;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
