package com.example.steps_tdee_calculator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})
@NoArgsConstructor
@Entity
@Table(name = "tdees")
public class Tdee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private double kcalFromSteps = 0;
    @Column(nullable = false)
    private LocalDate dateEntered;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private Tdee(TdeeBuilder builder) {
        this.value = builder.value;
        this.kcalFromSteps = builder.kcalFromSteps;
        this.dateEntered = builder.dateEntered;
        this.user = builder.user;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class TdeeBuilder {
        private double value;
        private double kcalFromSteps;
        private LocalDate dateEntered;
        private AppUser user;

        public TdeeBuilder setValue(double value) {
            this.value = value;
            return this;
        }

        public TdeeBuilder setKcalFromSteps(double kcalFromSteps) {
            this.kcalFromSteps = kcalFromSteps;
            return this;
        }

        public TdeeBuilder setDateEntered(LocalDate dateEntered) {
            this.dateEntered = dateEntered;
            return this;
        }

        public TdeeBuilder setUser(AppUser user) {
            this.user = user;
            return this;
        }

        public Tdee build() {
            return new Tdee(this);
        }
    }
}
