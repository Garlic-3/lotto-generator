package com.garlic3.lotto_generator.domian.lotto.repository;

import com.garlic3.lotto_generator.domian.lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {

    Optional<Lotto> findByMemberIdAndCreatedAtBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
