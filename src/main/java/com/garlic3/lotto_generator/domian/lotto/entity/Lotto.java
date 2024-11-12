package com.garlic3.lotto_generator.domian.lotto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "lotto")
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number1", nullable = false)
    private int number1;

    @Column(name = "number2", nullable = false)
    private int number2;

    @Column(name = "number3", nullable = false)
    private int number3;

    @Column(name = "number4", nullable = false)
    private int number4;

    @Column(name = "number5", nullable = false)
    private int number5;

    @Column(name = "number6", nullable = false)
    private int number6;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "lotto_type", nullable = false)
    private LottoType lottoType;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    // 정적 팩토리 메서드
    public static Lotto of(List<Integer> numbers, LottoType type
//            , User user
    ) {
        return Lotto.builder()
                .number1(numbers.get(0))
                .number2(numbers.get(1))
                .number3(numbers.get(2))
                .number4(numbers.get(3))
                .number5(numbers.get(4))
                .number6(numbers.get(5))
                .createdAt(LocalDateTime.now())
                .lottoType(type)
//                .user(user)
                .build();
    }

}
