package tacos.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Data;

@Data
@Entity
@Table(name="Taco_Order")
public class TacoOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	private Date placedAt = new Date();
	@NotBlank(message="Delivery name is required")
	private String deliveryName;
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	@NotBlank(message="City is required")
	private String deliveryCity;
	@NotBlank(message="State is required")
	private String deliveryState;
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	@Column(name = "cc_number")
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Column(name = "cc_expiration")
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	@Column(name = "cc_cvv")
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();
	@JoinTable(
			name = "taco_order_tacos",
			joinColumns = @JoinColumn(name = "taco_order_id"),
			inverseJoinColumns = @JoinColumn(name = "taco_id") // Явно указываем правильное имя
	)
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}