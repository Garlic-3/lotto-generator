package com.garlic3.lotto_generator.domian.lotto.service;

import com.garlic3.lotto_generator.domian.lotto.dto.TodayLuckLottoResponse;
import com.garlic3.lotto_generator.domian.lotto.entity.Lotto;
import com.garlic3.lotto_generator.domian.lotto.entity.LottoType;
import com.garlic3.lotto_generator.domian.lotto.repository.LottoRepository;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LottoService {

    private final LottoRepository lottoRepository;


    public TodayLuckLottoResponse generateLottoNumber(TodayLuckResponse todayLuck) {

        getTodayLottoIfExist(1L);


        List<Integer> lottoNumbers = null;

        if (existingLotto.isPresent()) {
            // 이미 오늘의 로또가 존재하는 경우, 번호 리스트 반환
            lottoNumbers = getLottoNumber();
        } else {
            // 새로운 로또 번호 생성 및 저장
            lottoNumbers = generateTodayNumber(todayLuck);
            saveLotto(lottoNumbers, LottoType.LUCK);
        }





        // 오늘의 로또 번호를 가져오거나 새로 생성
        List<Integer> lottoNumbers = getOrGenerateLottoNumbers(todayLuck);

        return TodayLuckLottoResponse.of(todayLuck, lottoNumbers);
    }

    private Optional<Lotto> getTodayLottoIfExist(Long userId) {
        // 오늘 날짜의 시작과 끝 범위 설정
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        // 오늘 날짜 범위로 기존 로또 조회
        return lottoRepository.findByMemberIdAndCreatedAtBetween(userId, startOfDay, endOfDay);
    }

    private List<Integer> toNumberList(Lotto lotto) {
        return Arrays.asList(
                lotto.getNumber1(),
                lotto.getNumber2(),
                lotto.getNumber3(),
                lotto.getNumber4(),
                lotto.getNumber5(),
                lotto.getNumber6()
        );
    }
    private List<Integer> generateTodayNumber(TodayLuckResponse todayLuck) {

        int hash = (todayLuck.getMoneyLuck() + todayLuck.getTotalLuck()).hashCode();
        Random random = new Random(hash);

        // TreeSet을 사용해 중복 제거 및 자동 정렬
        Set<Integer> numbers = new TreeSet<>();

        while (numbers.size() < 6) {
            // 1~45 사이의 숫자
            int number = random.nextInt(45) + 1;
            // TreeSet에 추가 (중복 자동 제거)
            numbers.add(number);
        }

        return new ArrayList<>(numbers);
    }

    private void saveLotto(List<Integer> lottoNumbers, LottoType lottoType) {
        Lotto lotto = Lotto.of(lottoNumbers, lottoType);
        lottoRepository.save(lotto);
    }

}
