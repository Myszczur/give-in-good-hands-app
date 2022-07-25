package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.coderslab.charity.service.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/register")
//    public String createUser() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("admin");
//        userService.saveUser(user);
//        return "/login/register";
//    }


}
