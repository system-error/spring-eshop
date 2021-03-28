package spring.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.eshop.model.Product;
import spring.eshop.model.User;
import spring.eshop.repository.UserRepository;
import spring.eshop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/success")
    public String displayUser(Model model, Principal principal,HttpServletRequest request)  {

        model.addAttribute("username",principal.getName());

        if(request.isUserInRole("USER")){
            System.out.println("USER here!!!");

            return "user";
        }

        return "/";

        ////        String role =  authResult.getAuthorities().toString();
//
//        if(role.contains("USER")){
////            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
//            return "user";
//        }
//        else if(role.contains("USER")) {
//            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
//        }
    }

    @GetMapping({"/administrator/dashboard","/admin/","/administrator/"})
    public String displayDashBoard(HttpServletRequest request,Model model, Principal principal) throws FileNotFoundException {
        model.addAttribute("username",principal.getName());
        if(request.isUserInRole("ADMIN")){
            System.out.println("ADMIN here!!!");
            return "admin/adminDashboard";
        }
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user",new User());
        return "userRegistrationForm";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setActive(true);
            user.setRoles("ROLE_USER");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
