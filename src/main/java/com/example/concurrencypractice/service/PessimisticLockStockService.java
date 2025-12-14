package com.example.concurrencypractice.service;

import com.example.concurrencypractice.domain.Stock;
import com.example.concurrencypractice.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Long decreaseStock(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decreaseQuantity(quantity);
        stockRepository.saveAndFlush(stock);

        return stock.getQuantity();
    }
}