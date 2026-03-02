package com.example.kafkaConsumer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wikimedia_recentchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WikiMediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String userName;
    private String type;
    private String wiki;
    private boolean bot;
}