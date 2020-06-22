package com.booking.demo.controller;

import com.booking.demo.entity.Flight;
import com.booking.demo.rpc.FlightService;
import com.booking.demo.rpc.MyResponse;
import com.booking.demo.rpc.PayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightController {
    @Autowired
    private PayService payService;
    @Autowired
    private FlightService flightService;

    private final ObjectMapper objectMapper;

    public FlightController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/query")
    public String query(@RequestParam String id,
                        @RequestParam String departure,
                        @RequestParam String destination,
                        @RequestParam String time) throws JsonProcessingException {
        System.out.println(" querying: " + id + "," + departure + "," + destination + "," + time);
        List<Flight> flights = flightService.query(id, departure, destination, time);
        System.out.println("  " + flights.size() + " flights found");
        String json = objectMapper.writeValueAsString(flights);
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/order")
    public String order(@RequestParam long uid,
                        @RequestParam String fid) throws JsonProcessingException {
        System.out.println(" " + uid + " ordering: " + fid);
        MyResponse response = flightService.order(uid, fid);
        String json = objectMapper.writeValueAsString(response);
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/bought")
    public String bought(@RequestParam long uid) throws JsonProcessingException {
        System.out.println(" " + uid + " bought... ");
        List<Flight> flights = flightService.bought(uid);
        String json = objectMapper.writeValueAsString(flights);
        System.out.println(json);
        return json;
    }

}
