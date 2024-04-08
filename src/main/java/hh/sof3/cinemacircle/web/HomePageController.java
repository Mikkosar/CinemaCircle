package hh.sof3.cinemacircle.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import hh.sof3.cinemacircle.domain.MovieRepository;
import hh.sof3.cinemacircle.domain.UserRepository;
import hh.sof3.cinemacircle.domain.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HomePageController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/home")
    public String homePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName);

        model.addAttribute("currentUser", user);
        model.addAttribute("movies", movieRepository.findAll());

        return "Home"; // Home.html
    } 
    
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login"; // login.html
    }

    @GetMapping(value = "/signup")
    public String SignUp(Model model) {

        model.addAttribute("user", new User());

        return "singup"; // singup.html
    }

    @PostMapping(value = "/saveuser")
    public String saveUser(@ModelAttribute User user) {

        user.setRole("USER");
        userRepository.save(user);

        return "redirect:/home";
    }
    
    @PostMapping(value = "/logout")
    public String logoutPage(){
        return "redirect:/login?logout";
    }
}
