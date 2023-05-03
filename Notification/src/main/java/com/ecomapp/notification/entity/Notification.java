package com.ecomapp.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Entity(name = "Notification")
@Table(name = "notification")
public class Notification {
    @Id
    private Long id;

    @Column(
            name = "customer_id",
            nullable = false
    )
    private Long customerId;

    @Column(
            name = "customer_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String customerName;

    @Column(
            name = "customer_email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String customerEmail;

    @Column(
            name = "sender",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String sender;

    @Column(
            name = "message",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

    @Column(
            name = "sent_at",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime sentAt;
}
