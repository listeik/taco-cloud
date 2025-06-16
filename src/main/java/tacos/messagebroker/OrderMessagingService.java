package tacos.messagebroker;

import tacos.TacoOrder;


public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

}