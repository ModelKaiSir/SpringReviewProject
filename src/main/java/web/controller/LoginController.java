package web.controller;

import domain.Boss;
import domain.Car;
import domain.LoginCommand;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private Boss myBoss;
    @Autowired
    private ArrayList<Car> cars;

    @RequestMapping("/index.html")
    public String loginPage() {

        return "login";
    }

    @RequestMapping("/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand command) {

        boolean success = userService.hasMatchUser(command.getUserName(), command.getPassword());
        if (success) {

            Optional<User> user = userService.findUserByName(command.getUserName());
            Assert.isTrue(user.isPresent());

            user.get().setLastIp(request.getLocalAddr());
            user.get().setLastVisit(new Date());
            userService.loginSuccess(user.get());
            request.getSession().setAttribute("user", user.get());
            ModelAndView result = new ModelAndView("main");
            result.addObject("boss", myBoss);
            System.out.println(myBoss.getNiceCar().getName());
            result.addObject("cars", cars);
            return result;
        } else {

            return new ModelAndView("login", "error", "用户名或密码错误！");
        }
    }
}
