package tacos.security;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.UserRepository;
import tacos.email.EmailService;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        try {
        emailService.sendOrderDetails(
                form.getEmail(),
                "Добро пожаловать, " + form.getFullname() + "!",
                "Спасибо за регистрацию!"
        );
        }
        catch (Exception e){
            log.info("Устарел пароль-код для email");
        }
        return "redirect:/login";
    }
}