package com.garlic3.lotto_generator.domian.luck.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckRequest;
import com.garlic3.lotto_generator.domian.luck.dto.TodayLuckResponse;
import com.garlic3.lotto_generator.domian.luck.entity.Luck;
import com.garlic3.lotto_generator.domian.luck.repository.LuckRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class LuckService {

    private final LuckRepository luckRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public void saveLuckData(String birthday) throws JsonProcessingException {
        // URL을 구성하는 변수
        String baseUrl = "https://m.search.naver.com/p/csearch/content/apirender.nhn";
        String where = "nexearch";
        String pkid = "387";
        String query = "%EC%83%9D%EB%85%84%EC%9B%94%EC%9D%BC+%EC%9A%B4%EC%84%B8";

        // URL 구성
        String url = String.format("%s?where=%s&pkid=%s&u2=%s&q=%s", baseUrl, where, pkid, birthday, query);

        // API 호출
        String response = restTemplate.getForObject(url, String.class);

        // JSON 데이터에서 첫 번째 `flick` 항목 추출 및 HTML 파싱
        if (response != null) {
            // JSON 응답에서 필요한 부분 추출
            String jsonString = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            // `flick` 배열에서 첫 번째 항목 추출
            JsonNode firstFlickItem = rootNode.get("flick").get(0);
            String htmlString = firstFlickItem.asText();

            // HTML 파싱
            Document doc = Jsoup.parse(htmlString);

            // 총운과 금전운 텍스트 추출
            Elements totalLuckElement = doc.select("dt:contains(총운) + dd p");
            Elements moneyLuckElement = doc.select("dt:contains(금전운) + dd p");

            // 결과를 텍스트로 저장
            String totalLuck = totalLuckElement.text();
            String moneyLuck = moneyLuckElement.text();

            log.debug("totalLuck = {}", totalLuck);
            log.debug("moneyLuck = {}", moneyLuck);

            // 총운과 금전운만 저장
            saveLuck(totalLuck, moneyLuck);
        }
    }

    private void saveLuck(String totalLuckContent, String moneyLuckContent) {

        Luck luck = Luck.builder()
                .totalLuck(totalLuckContent)
                .moneyLuck(moneyLuckContent)
                .build();

        luckRepository.save(luck);
    }

    public TodayLuckResponse getTodayLuck(TodayLuckRequest todayLuckRequest) {
        long totalLuckCount = getTotalLuckCount();
        long luckIndex = calculateIndex(todayLuckRequest, totalLuckCount);
        Luck luck = luckRepository.findById(luckIndex)
                .orElseThrow(() -> new NoSuchElementException("운세를 찾을 수 없습니다."));

        return new TodayLuckResponse(luck.getTotalLuck(), luck.getMoneyLuck());
    }

    private long getTotalLuckCount() {
        return luckRepository.count();
    }

    private long calculateIndex(TodayLuckRequest todayLuckRequest, long totalItems) {
        String key = todayLuckRequest.getName() + todayLuckRequest.getBirthDate() + LocalDate.now();
        // 이름과 생년월일로 해시 생성
        int hash = key.hashCode();
        Random random = new Random(hash);
        // 랜덤 long 생성
        long randomLong = random.nextLong();
        // 총 운세 개수에 맞춰 인덱스 선택
        return Math.abs(randomLong % totalItems);
    }


}
