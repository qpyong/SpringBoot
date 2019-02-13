package org.qpyong.demos.springboot.controller.jpa;

import org.qpyong.demos.springboot.domain.User;
import org.qpyong.demos.springboot.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 请求参数版本的添加用户。并返回视图名(模板页面：freemarker.ftl/thymeleaf.html）
     * <p>使用freemarker模板，必须设置spring.freemarker.request-context-attribute=request
     * <p>使用thymeleaf模板，
     *
     * @return
     */
    @PostMapping(path = "/add")
    public String addNewUser(Model model, @RequestParam String userName, @RequestParam String password, @RequestParam String email) {
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "freemarker";
    }

}
