package com.booking.demo.controller;

import com.booking.demo.airline.FlightServiceImpl;
import com.booking.demo.entity.Flight;
import com.booking.demo.entity.User;
import com.booking.demo.repo.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private final RedisTemplate<String, Object> redisTemplate;
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

    private void showFlight(Object o) {
        Flight f = (Flight) o;
        System.out.println("id:" + f.getId() + " dp:" + f.getDeparture()
                + " dt:" + f.getDestination() + " t:" + f.getTime()
                + " p:" + f.getPrice() + " c:" + f.getCount());
        System.out.println("...");
    }

    private void showFlights(List<Object> l) {
        System.out.println("begin...");
        if (l != null)
            for (Object o : l)
                showFlight(o);
        System.out.println("end...");
    }

    @RequestMapping(value = "/redis/init/{id}/{departure}/{destination}/{time}/{price}/{count}")
    public void redisInit(@PathVariable String id,
                          @PathVariable String departure,
                          @PathVariable String destination,
                          @PathVariable String time,
                          @PathVariable long price,
                          @PathVariable long count
    ) {
        System.out.println("initing: " + id + " " + departure + " " + destination + " " + time + " " + price + " " + count);

        Flight flight = new Flight();
        flight.setId(id);
        flight.setDeparture(departure);
        flight.setDestination(destination);
        flight.setTime(time);
        flight.setPrice(price);
        flight.setCount(count);

        String ID = "flight:" + id;
        redisTemplate.opsForValue().set(ID, flight);
        redisTemplate.opsForSet().add("departure:" + departure, ID);
        redisTemplate.opsForSet().add("destination:" + destination, ID);
        redisTemplate.opsForSet().add("time:" + time.substring(0, 8), ID);

        ArrayList<String> keys = new ArrayList<>();

        keys.add("departure:" + departure);
        keys.add("destination:" + destination);
        keys.add("time:" + time);

        showFlights(redisTemplate.opsForValue().multiGet(redisTemplate.keys("flight:" + "*")));
        showFlight(redisTemplate.opsForValue().get(ID));
        Set<Object> so = redisTemplate.opsForSet().intersect(keys);
        List<String> ls = new ArrayList<>();
        for (Object o : so)
            ls.add((String)o);
        showFlights(redisTemplate.opsForValue().multiGet(ls));
//        System.out.println(redisTemplate.opsForSet().intersect(keys));
    }

    @RequestMapping(value = "/mysql/user")
    public String mysqlGetUser() throws JsonProcessingException {
        if (userRepo.findAll().isEmpty()) {
            User user = new User();
            user.setBalance(1234500);
            user.setName("user1");
            user.setPassword("password1");
            userRepo.save(user);
        }
        return new ObjectMapper().writeValueAsString(userRepo.findAll());
    }

}
