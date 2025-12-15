package com.example.concurrencypractice.facade;

import com.example.concurrencypractice.service.StockService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final StockService stockService;

    public void decrease(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());

        try {
            boolean available = lock.tryLock(30, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("lock 획득 실패");
            }

            stockService.decreaseStock(id, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
