package pl.coderslab.charity.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {


    @GetMapping("/admin")
    public String adminPanel() {
        return "/admin/adminPanel";
    }
}
