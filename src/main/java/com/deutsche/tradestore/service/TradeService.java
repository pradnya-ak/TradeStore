package com.deutsche.tradestore.service;

import com.deutsche.tradestore.models.Trade;
import com.deutsche.tradestore.repository.TradeRepository;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    private List<Trade> trades = new ArrayList<>(Arrays.asList(
            new Trade("T1", "1", "CP-1", "B1", new Date(), new Date(), false),
            new Trade("T2", "1", "CP-1", "B1", new Date(), new Date(), false),
            new Trade("T3", "1", "CP-1", "B1", new Date(), new Date(), false)));

    public List<Trade> getAllTrades(){
        List<Trade> trades = new ArrayList<>();

        tradeRepository.findAll().forEach(trades::add);
        return trades;
    }

    public void addTrade(Trade trade){
        tradeRepository.save(trade);
    }

    public Trade getTradeById(String id) {

        return null; //tradeRepository.findById(id);
    }
}
