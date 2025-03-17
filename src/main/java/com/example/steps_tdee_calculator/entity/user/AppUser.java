package com.example.steps_tdee_calculator.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {
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
    @ColumnDefault("'USER'")
    private String role;

    private AppUser(UserBuilder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.surname = builder.surname;
        this.password = builder.password;
        this.role = builder.role;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserBuilder {
        private String username;
        private String name;
        private String surname;
        private String password;
        private String role = "USER";

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

        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public AppUser build() {
            return new AppUser(this);
        }
    }
}
