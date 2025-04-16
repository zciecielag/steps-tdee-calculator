package com.example.steps_tdee_calculator.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tdee> tdeeList;
    @Column
    private double bmr;
    @Column
    private double height;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Weight> weightList;
    @Column
    private int age;
    @ColumnDefault("'F'")
    private String gender;
    @ColumnDefault("'USER'")
    private String role;

    private AppUser(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.tdeeList = builder.tdeeList;
        this.bmr = builder.bmr;
        this.height = builder.height;
        this.weightList = builder.weightList;
        this.age = builder.age;
        this.gender = builder.gender;
        this.role = builder.role;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserBuilder {
        private String username;
        private String password;
        private List<Tdee> tdeeList;
        private double bmr;
        private double height;
        private List<Weight> weightList;
        private int age;
        private String gender;
        private String role = "USER";

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setTdeeList(List<Tdee> tdeeList) {
            this.tdeeList = tdeeList;
            return this;
        }

        public UserBuilder setBmr(double bmr) {
            this.bmr = bmr;
            return this;
        }

        public UserBuilder setHeight(double height) {
            this.height = height;
            return this;
        }

        public UserBuilder setWeightList(List<Weight> weightList) {
            this.weightList = weightList;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setGender(String gender) {
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
