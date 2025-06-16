package tacos.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.messagebroker.OrderMessagingService;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080") // For development purpose https://127.0.0.1:8443
public class OrderApiController {

    private final OrderRepository orderRepo;

    private final OrderMessagingService message;

    @Autowired
    OrderApiController(OrderRepository orderRepo, OrderMessagingService message) {
        this.orderRepo = orderRepo;
        this.message = message;
    }

    @GetMapping(path="/{orderId}")
    public TacoOrder getOrder(@PathVariable("orderId") Long orderId){
        TacoOrder tacoOrder = orderRepo.findById(orderId).get();
        return tacoOrder;
    }

    @PostMapping(consumes = "application/json")
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        TacoOrder sentTaco = orderRepo.save(order);
        message.sendOrder(sentTaco);
        return sentTaco;
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody TacoOrder order) {
        order.setId(orderId);
        return orderRepo.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody TacoOrder patch) {
        TacoOrder order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }

}