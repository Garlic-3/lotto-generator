package com.garlic3.lotto_generator.domian.luck.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "luck")
public class Luck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_luck", nullable = false, length = 500)
    private String totalLuck;

    @Column(name = "money_luck", nullable = false, length = 500)
    private String moneyLuck;
}