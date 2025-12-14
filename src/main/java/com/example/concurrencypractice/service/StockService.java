package com.example.concurrencypractice.service;

import com.example.concurrencypractice.domain.Stock;
import com.example.concurrencypractice.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

//    @Transactional
    public synchronized void decreaseStock (Long id, Long quantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품의 재고를 찾을 수 없습니다. ID: " + id));
        stock.decreaseQuantity(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
