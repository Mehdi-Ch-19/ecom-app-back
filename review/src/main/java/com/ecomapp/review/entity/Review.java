package com.ecomapp.review.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Review {
    @Id
    @SequenceGenerator(
            name = "review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private Long customerId;
    private int rating;
    private String comment;
    private  String productId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reviewedAt;
}
