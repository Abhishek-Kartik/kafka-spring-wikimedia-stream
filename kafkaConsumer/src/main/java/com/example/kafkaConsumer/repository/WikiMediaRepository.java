package com.example.kafkaConsumer.repository;

import com.example.kafkaConsumer.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMediaRepository extends JpaRepository<WikiMediaData, Long> {
}