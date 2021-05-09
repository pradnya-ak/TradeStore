package com.deutsche.tradestore.service;

import com.deutsche.tradestore.models.Trade;
import com.deutsche.tradestore.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades(){
        List<Trade> trades = new ArrayList<>();
        tradeRepository.findAll().forEach(trades::add);
        return trades;
    }

    public Optional<Trade> getTradeById(String id) {
      return tradeRepository.findById(id);
    }

    public void addTrade(Trade trade){

        tradeRepository.save(trade);
    }

    public String deleteTradeById(String id){
        tradeRepository.deleteById(id);
        return "SUCCESS";
    }
}
