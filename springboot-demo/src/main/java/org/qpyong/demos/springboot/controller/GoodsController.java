package org.qpyong.demos.springboot.controller;

import org.qpyong.demos.springboot.domain.Goods;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class GoodsController {
    @RequestMapping("/goods/{id}")
    public Goods getGoods(@PathVariable String id) {
        Goods goods = new Goods();
        goods.setId(Long.parseLong(id));
        goods.setGoodsName("诺基亚X7");
        goods.setPrice(BigDecimal.valueOf(1800L,0));
        goods.setValidMonth(24);
        return goods;
    }
}
