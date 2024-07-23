package web.Webperfume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.Webperfume.model.User;
import web.Webperfume.model.Order;
import web.Webperfume.service.AdminService;
import web.Webperfume.service.UserService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listusers")
    public String listUsers(Model model) {
        List<User> users = adminService.getUsers();
        model.addAttribute("users", users);
        return "admin/admin-all-user"; // Đảm bảo tên template là chính xác
    }

    @GetMapping("/listorders")
    public String listOrders(Model model) {
        List<Order> orders = adminService.getOrders();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Order order : orders) {
            if (order.getOrderDate() != null) {
                order.setOrderDateFormatted(order.getOrderDate().format(formatter));
            }
        }
        model.addAttribute("orders", orders);
        return "admin/admin-all-order"; // Đảm bảo tên template là chính xác
    }
}
