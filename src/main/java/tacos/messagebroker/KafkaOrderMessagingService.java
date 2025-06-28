package tacos.messagebroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tacos.model.TacoOrder;
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private KafkaTemplate<String, TacoOrder> kafkaTemplate;
    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}