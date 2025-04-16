package com.example.steps_tdee_calculator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "weights")
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double value;
    @Column(nullable = false)
    private LocalDate dateEntered;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private Weight(WeightBuilder builder) {
        this.value = builder.value;
        this.dateEntered = builder.dateEntered;
        this.user = builder.user;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeightBuilder {
        private double value;
        private LocalDate dateEntered;
        private AppUser user;

        public WeightBuilder setValue(double value) {
            this.value = value;
            return this;
        }

        public WeightBuilder setDateEntered(LocalDate dateEntered) {
            this.dateEntered = dateEntered;
            return this;
        }

        public WeightBuilder setUser(AppUser user) {
            this.user = user;
            return this;
        }

        public Weight build() {
            return new Weight(this);
        }
    }
}
