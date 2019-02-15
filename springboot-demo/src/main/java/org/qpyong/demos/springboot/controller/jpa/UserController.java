package org.qpyong.demos.springboot.controller.jpa;

import com.alibaba.fastjson.JSONObject;
import org.qpyong.demos.springboot.domain.User;
import org.qpyong.demos.springboot.domain.UserRepository;
import org.qpyong.demos.springboot.service.redis.RedisService;
import org.qpyong.demos.springboot.vo.ResponseVO;
import org.qpyong.demos.springboot.vo.UserPageSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
            return (User) JSONObject.parseObject(userStr, User.class);
        }
        return userRepository.findOne(Integer.valueOf(userId));
    }

    /**
     * 分页示例：简单查询(user_name = 'xx' and dept_id = 'xx')
     *
     * @return
     */
    @GetMapping(path = "/list")
    @ResponseBody
    public ResponseVO listUsers(UserPageSearchVO vo) {
        Pageable pageable = new PageRequest(vo.getPageIndex(), vo.getPageSize());
        User user = new User();
        if(!StringUtils.isEmpty(vo.getUserName()))
            user.setUserName(vo.getUserName());
        if(!StringUtils.isEmpty(vo.getDeptId())) {
            user.setDeptId(vo.getDeptId());
        }
        Page<User> page = null;
        if(user.isEmpty()) {
            /**
             * 设定user的属性和Matcher方式，作为查询条件
             * <p>
             * ExampleMatcher.matchingAny()指定任意匹配，即所有的条件都分别满足的记录数都会返回
             */
            Example<User> example = Example.of(user, ExampleMatcher.matchingAny());
            page = userRepository.findAll(example, pageable);
        } else
            page = userRepository.findAll(pageable);
        return ResponseVO.createResponseFromPage(page);
    }

    /**
     * 分页示例：对多字段设置匹配(userName like '%xx' and dept_id = 'xx')
     *
     * @return
     */
    @GetMapping(path = "/listMore")
    @ResponseBody
    public ResponseVO listUsersByMultiProperties(UserPageSearchVO vo) {
        Pageable pageable = new PageRequest(vo.getPageIndex(), vo.getPageSize());
        User user = new User();
        user.setUserName(vo.getUserName());
        user.setDeptId(vo.getDeptId());
        /**
         * userName的匹配方式：以vo.getUserName()结尾，即%xxx
         */
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("userName",
                        ExampleMatcher.GenericPropertyMatchers.endsWith());
        Example<User> example = Example.of(user, matcher);
        Page<User> page = userRepository.findAll(example, pageable);
        return ResponseVO.createResponseFromPage(page);
    }

    /**
     * 分页示例：对多字段设置匹配
     *
     * @return
     */
    @GetMapping(path = "/userName/{username}")
    @ResponseBody
    public ResponseVO listUsersByUsername(@PathVariable String username) {
        User user = userRepository.queryByUserName(username);
        return ResponseVO.createResponseFromEntity(user);
    }

}
