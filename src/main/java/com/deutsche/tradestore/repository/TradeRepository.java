package com.deutsche.tradestore.repository;

import com.deutsche.tradestore.entity.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeRepository extends CrudRepository<Trade, Integer> {

    public List<Trade> findByTradeIdOrderByVersionDesc(String tradeId);
}
