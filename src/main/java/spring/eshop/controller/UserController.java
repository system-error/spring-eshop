package spring.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.eshop.model.User;
import spring.eshop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String displayUser()  {
//        if(request.isUserInRole("USER")){
//            System.out.println("USER here!!!");
//
//            return "user";
//        }

////        String role =  authResult.getAuthorities().toString();
//
//        if(role.contains("USER")){
////            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
//            return "user";
//        }
//        else if(role.contains("USER")) {
//            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
//        }

        return "user";
    }

    @GetMapping("/administrator/dashboard")
    public String displayDashBoard(HttpServletRequest request) throws FileNotFoundException {
        if(request.isUserInRole("USER")){
            System.out.println("USER here!!!");

            return "adminDashboard";
        }

        if(request.isUserInRole("ADMIN")){
            System.out.println("ADMIN here!!!");
            return "administrator/adminDashboard";
        }
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        System.out.println("admin!!!");

        model.addAttribute("user",new User());

        return "userRegistrationForm";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
            userRepository.save(user);

            return "registerSuccess";
    }

//    @GetMapping("/password")
//    public String password() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = bCryptPasswordEncoder.encode("pass");
//        return encodedPassword;
//    }
}
