package org.qpyong.demos.springboot.controller.first;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.qpyong.demos.springboot.common.config.Department;
import org.qpyong.demos.springboot.common.config.Environments;
import org.qpyong.demos.springboot.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * <code>@RestController</code>等同于<code>@Controller</code>和<code>@ResponseBody</code>（用于方法）联合使用.
 */
@RestController
public class GoodsController {
    @Autowired
    Environment evn;

    @Autowired
    Environments environment;


    @Autowired
    Department department;

    @RequestMapping("/goods/{id}")
    public Goods getGoods(@PathVariable String id) {
        Goods goods = new Goods();
        goods.setId(Long.parseLong(id));
        goods.setGoodsName("诺基亚X7");
        goods.setPrice(BigDecimal.valueOf(1800L, 0));
        goods.setValidMonth(24);
        Calendar cal = Calendar.getInstance();
        cal.set(2019, 3, 27
        );
        goods.setProduceDate(cal.getTime());
        System.out.println(evn.getProperty("app.department.name"));
        System.out.println(department == null);
        System.out.println(environment == null);
        System.out.println(evn.getProperty("single.property.value"));
        return goods;
    }
}
