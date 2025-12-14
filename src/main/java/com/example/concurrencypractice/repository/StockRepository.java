package com.example.concurrencypractice.repository;

import com.example.concurrencypractice.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
