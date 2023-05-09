package com.ecomapp.notification.service;

import com.ecomapp.feign.notification.NotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Transport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    public EmailSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendOrderConfirmationEmail(NotificationRequest notificationRequest) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("chihebrca2002@gmail.com");
        helper.setTo(notificationRequest.getCustomerEmail());
        helper.setSubject("Order Confirmation N': " +notificationRequest.getOrderPlaced().getOrderId());
        Context context = new Context();
        context.setVariable("orderId",notificationRequest.getOrderPlaced().getOrderId());
        context.setVariable("orderDate", LocalDateTime.from(notificationRequest.getOrderPlaced().getOrderDate()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        context.setVariable("TotalAmount",notificationRequest.getOrderPlaced().getTotalamount());
        context.setVariable("productItems",notificationRequest.getOrderPlaced().getProductItems());
        String htmlContent = templateEngine.process("mailTemplate",context);
        message.setContent(htmlContent,"text/html");
        mailSender.send(message);

    }
}
