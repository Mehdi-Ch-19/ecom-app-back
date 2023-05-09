package com.ecomapp.notification.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Notification {
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}
