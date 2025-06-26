package tacos.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessageChannel;


@Slf4j
@Configuration
public class EmailIntegrationConfig {

    // Асинхронный канал с буфером на 100 сообщений
    @Bean
    public MessageChannel emailChannel() {
        return new QueueChannel(100);
    }

    // Поток обработки email
    @Bean
    public IntegrationFlow emailFlow(JavaMailSender mailSender) {
        return IntegrationFlow.from("emailChannel")
                .handle(m -> {
                    SimpleMailMessage message = (SimpleMailMessage) m.getPayload();
                    mailSender.send(message);
                    log.info("Email sent to: {}", message.getTo()[0]); // Логирование
                })
                .get();
    }
}