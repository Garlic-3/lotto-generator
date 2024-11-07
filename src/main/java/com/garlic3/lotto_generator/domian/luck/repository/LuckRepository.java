package com.garlic3.lotto_generator.domian.luck.repository;

import com.garlic3.lotto_generator.domian.luck.entity.Luck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuckRepository extends JpaRepository<Luck, Long> {
}
