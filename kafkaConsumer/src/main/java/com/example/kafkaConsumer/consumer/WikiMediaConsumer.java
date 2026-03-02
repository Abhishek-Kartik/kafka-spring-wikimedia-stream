package com.example.kafkaConsumer.consumer;


import com.example.kafkaConsumer.entity.WikiMediaData;
import com.example.kafkaConsumer.repository.WikiMediaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikiMediaConsumer {

    private final WikiMediaRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "wikimedia-stream", groupId = "myGroup")
    public void consumeMsg(String msg) {
        try {
            JsonNode jsonNode = objectMapper.readTree(msg);

            WikiMediaData data = WikiMediaData.builder()
                    .title(jsonNode.get("title").asText())
                    .userName(jsonNode.get("user").asText())
                    .type(jsonNode.get("type").asText())
                    .wiki(jsonNode.get("wiki").asText())
                    .bot(jsonNode.get("bot").asBoolean())
                    .build();

            repository.save(data);

            log.info("Saved event for title: {}", data.getTitle());

        } catch (Exception e) {
            log.error("Error processing message", e);
        }
    }
}
