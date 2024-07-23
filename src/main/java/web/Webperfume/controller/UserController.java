//package web.Webperfume.controller;
//
//import web.Webperfume.model.User;
//import web.Webperfume.service.UserService;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//    private final JavaMailSender mailSender;
//
//    @GetMapping("/login")
//    public String login() {
//        return "users/login";
//    }
//
//    @GetMapping("/register")
//    public String register(@NotNull Model model) {
//        model.addAttribute("user", new User());
//        return "users/register";
//    }
//
//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("user") User user,
//                           @NotNull BindingResult bindingResult,
//                           Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "users/register";
//        }
//        userService.save(user);
//        userService.setDefaultRole(user.getUsername());
//        return "redirect:/login";
//    }
//}

package web.Webperfume.controller;

import web.Webperfume.model.User;
import web.Webperfume.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JavaMailSender mailSender;

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           @NotNull BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "users/register";
        }

        // Check if username or email already exists
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("errors", new String[]{"Username already exists"});
            return "users/register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("errors", new String[]{"Email already exists"});
            return "users/register";
        }

        userService.save(user);
        userService.setDefaultRole(user.getUsername());

        // Add a success message
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
        return "redirect:/login";
    }

    // Optional: You can add an endpoint for OAuth2 callback
    @GetMapping("/oauth2/callback")
    public String oauth2Callback() {
        // Handle OAuth2 callback logic here
        return "redirect:/home"; // Redirect to the desired page after OAuth2 login
    }
}

