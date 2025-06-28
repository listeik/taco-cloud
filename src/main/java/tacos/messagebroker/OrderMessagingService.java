package tacos.messagebroker;

import tacos.model.TacoOrder;


public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

}