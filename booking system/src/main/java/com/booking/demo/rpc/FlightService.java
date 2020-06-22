package com.booking.demo.rpc;

import com.booking.demo.entity.Flight;

import java.util.List;

public interface FlightService extends RPCService {
    List<Flight> query(String id, String departure, String destination, String time); // 浏览
    MyResponse order(long uid, String fid); // 下单
    List<Flight> bought(long uid); // 查看已购机票
}
