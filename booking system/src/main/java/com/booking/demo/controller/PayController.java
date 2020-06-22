package com.booking.demo.controller;

import com.booking.demo.entity.User;
//import com.booking.demo.rpc.DynamicProxyFactory;
import com.booking.demo.rpc.MyResponse;
import com.booking.demo.rpc.PayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService payService;

    private final ObjectMapper objectMapper;

    public PayController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
//        this.payService = DynamicProxyFactory.getService(PayService.class);
    }

    @RequestMapping(value = "/user/{id}")
    public String query(@PathVariable long id) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(payService.query(id));
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/save/{id}/{cash}")
    public String save(@PathVariable long id, @PathVariable long cash) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(payService.save(id, cash));
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/withdraw/{id}/{cash}")
    public String query(@PathVariable long id, @PathVariable long cash) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(payService.withdraw(id, cash));
        System.out.println(json);
        return json;
    }
}
