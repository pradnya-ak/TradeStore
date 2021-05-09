package com.deutsche.tradestore.repository;

import com.deutsche.tradestore.entity.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeRepository extends CrudRepository<Trade, Integer> {

    /*
    * Gets list of trade using its Trade Id and orders list by its version in descending order
    * @param tradeId
    * @return List<Trade>
    * */
    public List<Trade> findByTradeIdOrderByVersionDesc(String tradeId);
}
