package com.booking.demo.pay;

import com.booking.demo.entity.User;
import com.booking.demo.repo.UserRepo;
import com.booking.demo.rpc.MyResponse;
import com.booking.demo.rpc.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayServiceImpl implements PayService {
    private final UserRepo userRepo;

    @Autowired
    public PayServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean login(long id, String password) {
        Optional<User> user = userRepo.findById(id);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    @Override
    public User query(long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    @Override
    public MyResponse save(long id, long cash) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent())
            return MyResponse.fail("用户不存在");
        user.get().setBalance(user.get().getBalance() + cash);
        userRepo.save(user.get());
        return MyResponse.success(user.get().getBalance());
    }

    @Override
    public MyResponse withdraw(long id, long cash) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent())
            return MyResponse.fail("用户不存在");
        long balance = user.get().getBalance();
        if (balance < cash)
            return MyResponse.fail("余额不足");
        else {
            user.get().setBalance(user.get().getBalance() - cash);
            userRepo.save(user.get());
            return MyResponse.success(user.get().getBalance());
        }
    }
}
