package org.qpyong.demos.springboot.controller.jpa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.qpyong.demos.springboot.domain.User;
import org.qpyong.demos.springboot.domain.UserRepository;
import org.qpyong.demos.springboot.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.org.mozilla.javascript.internal.json.JsonParser;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;


    /**
     * 请求参数版本的添加用户。并返回视图名(模板页面：freemarker.html/thymeleaf.html）
     * <p>使用freemarker模板，必须设置spring.freemarker.request-context-attribute=request
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
        redisService.put("users", user.getId().toString(), JSONObject.toJSONString(user));
        return "freemarker";
    }

    @GetMapping(path = "/{userId}")
    @ResponseBody
    public User getUser(@PathVariable String userId) {
        if(redisService.exists("users", userId)) {
            String userStr = redisService.get("users", userId);
            return (User)JSONObject.parseObject(userStr,User.class);
        }
        return userRepository.findOne(Integer.valueOf(userId));
    }

}
