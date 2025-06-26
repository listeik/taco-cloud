package tacos.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final MessageChannel emailChannel;

    public EmailService(MessageChannel emailChannel) {
        this.emailChannel = emailChannel;
    }

    public void sendOrderDetails(String toEmail, String topic, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(topic);
        message.setText(content);

        emailChannel.send(MessageBuilder.withPayload(message).build());
    }
}