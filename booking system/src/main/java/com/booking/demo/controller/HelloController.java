package com.booking.demo.controller;

import com.booking.demo.entity.User;
import com.booking.demo.repo.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private final RedisTemplate<String,Object> redisTemplate;
    private final UserRepo userRepo;

    @Autowired
    public HelloController(RedisTemplate<String, Object> redisTemplate, UserRepo userRepo) {
        this.redisTemplate = redisTemplate;
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/redis/get/{key}")
    public String redisGet(@PathVariable String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @RequestMapping(value = "/redis/set/{key}/{value}")
    public void redisSet(@PathVariable String key,
                         @PathVariable String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @RequestMapping(value = "/mysql/user")
    public String mysqlGetUser() throws JsonProcessingException {
        User user = new User();
        user.setBalance(10000);
        user.setName("admin");
        user.setPassword("pswd");
        userRepo.save(user);
        return new ObjectMapper().writeValueAsString(userRepo.findAll());
    }

}
