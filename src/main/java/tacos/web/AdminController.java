package tacos.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.OrderRepository;
import tacos.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    @PreAuthorize("hasRole('Admin')")
    public String deleteAllOrders(){
        adminService.deleteAllOrders();
        return "redirect:admin";
    }

}
