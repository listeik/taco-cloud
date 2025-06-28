package tacos.web;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.model.TacoOrder;
import tacos.model.User;
import tacos.data.OrderRepository;
import tacos.email.EmailService;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	private OrderRepository orderRepo;
	private EmailService emailService;

	public OrderController(EmailService emailService, OrderRepository orderRepo) {
		this.emailService = emailService;
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid TacoOrder order, Errors errors,
							   SessionStatus sessionStatus,
							   @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		try {
		String contentTaco = "Вы заказали:\n";
		for (Taco taco : order.getTacos()) {
			contentTaco+="    " + taco.getName()+":\n";
			for(Ingredient ingredient:taco.getIngredients()){
				contentTaco+="        " + ingredient.getName()+"\n";
			}
		}

		emailService.sendOrderDetails(
				user.getEmail(),
				"Ваш заказ, " + user.getFullname() + "!",
				contentTaco
		);}
		catch (Exception e){
			log.info("Устарел пароль-код для email");
		}
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}