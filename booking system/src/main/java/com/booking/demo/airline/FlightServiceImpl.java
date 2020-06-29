package com.booking.demo.airline;

import com.booking.demo.config.TimeConfig;
import com.booking.demo.entity.Flight;
import com.booking.demo.entity.Ticket;
import com.booking.demo.repo.FlightRepo;
import com.booking.demo.repo.TicketRepo;
import com.booking.demo.rpc.FlightService;
import com.booking.demo.rpc.MyResponse;
import com.booking.demo.rpc.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepo flightRepo;
    private final TicketRepo ticketRepo;
    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PayService payService;

    @Autowired
    public FlightServiceImpl(FlightRepo flightRepo, TicketRepo ticketRepo, RedisTemplate<String, Object> redisTemplate) {
        this.flightRepo = flightRepo;
        this.ticketRepo = ticketRepo;
        this.redisTemplate = redisTemplate;
    }
    
    private List<Flight> getFlightsFromKeySet(Set<Object> tempSet) { // 从 id 集合获得航班列表
        List<String> allKeys = new ArrayList<>();
        for (Object o : tempSet)
            allKeys.add((String) o);
        List<Object> tempList = redisTemplate.opsForValue().multiGet(allKeys);
        assert tempList != null;
        List<Flight> list = new ArrayList<>();
        for (Object o : tempList)
            list.add((Flight) o);
        return list;
    }

    @Override
    public List<Flight> query(String id, String departure, String destination, String time) {
        List<Flight> list = new ArrayList<>();

        if (!id.equals("")) { // 有唯一 id
            list.add((Flight) redisTemplate.opsForValue().get("flight:" + id));
            System.out.println("   id found (" + id + ")");
            return list;
        }

        ArrayList<String> keys = new ArrayList<>();
        if (!departure.equals("")) {
            System.out.println("   departure found (" + departure + ")");
            keys.add("departure:" + departure);
        }
        if (!destination.equals("")) {
            System.out.println("   destination found (" + destination + ")");
            keys.add("destination:" + destination);
        }
        if (!time.equals("")) {
            System.out.println("   time found (" + time.substring(0, 8) + ")");
            keys.add("time:" + time.substring(0, 8));
        }

        if (keys.isEmpty()) { // 无限定条件，输出全部航班
            Set<String> allKeys = redisTemplate.keys("flight:" + "*");
            List<Object> tempList = redisTemplate.opsForValue().multiGet(allKeys);
            assert tempList != null;
            for (Object o : tempList)
                list.add((Flight) o);
        } else if (keys.size() == 1) { // 单个条件，输出满足该条件的全部航班
            Set<Object> tempSet = redisTemplate.opsForSet().members(keys.get(0));
            assert tempSet != null;
            list = getFlightsFromKeySet(tempSet);
        }
        else { // 多个条件，取交集
            Set<Object> tempSet = redisTemplate.opsForSet().intersect(keys);
            assert tempSet != null;
            list = getFlightsFromKeySet(tempSet);
        }
        return list;
    }

    @Override
    public MyResponse order(long uid, String fid) {
        Flight flight = (Flight) redisTemplate.opsForValue().get("flight:" + fid);
        if (flight == null)
            return MyResponse.fail("航班不存在");
        MyResponse result = payService.withdraw(uid, flight.getPrice());
        if (result.getStatus() == MyResponse.FAIL) {
            if ("用户不存在".equals(result.getMsg()))
                return MyResponse.fail("用户不存在");
            else if ("余额不足".equals(result.getMsg()))
                return MyResponse.fail("余额不足");
        }

        Ticket ticket = new Ticket();
        ticket.setFid(fid);
        ticket.setUid(uid);
        ticket.setTime(TimeConfig.now());
        ticketRepo.save(ticket);

        flight.setCount(flight.getCount() - 1);
        redisTemplate.opsForValue().set("flight:" + fid, flight);

        return MyResponse.success("当前余额：" + result.getData());
    }

    @Override
    public List<Flight> bought(long uid) {
        List<Ticket> tickets = ticketRepo.findByUid(uid);
        List<Flight> flights = new ArrayList<>();
        for (Ticket ticket : tickets)
            flights.add((Flight) redisTemplate.opsForValue().get("flight:" + ticket.getFid()));
        return flights;
    }
}
