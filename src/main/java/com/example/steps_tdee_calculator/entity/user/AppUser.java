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
    @Column(nullable = false, length = 100)
    private String password;
    @Column
    private double currentTdee;
    @Column
    private double currentBmr;
    @Column
    private double height;
    @Column
    private double weight;
    @Column
    private int age;
    @Column
    private boolean gender;
    @ColumnDefault("'USER'")
    private String role;

    private AppUser(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.currentTdee = builder.currentTdee;
        this.currentBmr = builder.currentBmr;
        this.height = builder.height;
        this.weight = builder.weight;
        this.age = builder.age;
        this.gender = builder.gender;
        this.role = builder.role;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserBuilder {
        private String username;
        private String password;
        private double currentTdee;
        private double currentBmr;
        private double height;
        private double weight;
        private int age;
        private boolean gender;
        private String role = "USER";

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setCurrentTdee(double currentTdee) {
            this.currentTdee = currentTdee;
            return this;
        }

        public UserBuilder setCurrentBmr(double currentBmr) {
            this.currentBmr = currentBmr;
            return this;
        }

        public UserBuilder setHeight(double height) {
            this.height = height;
            return this;
        }

        public UserBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setGender(boolean gender) {
            this.gender = gender;
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
