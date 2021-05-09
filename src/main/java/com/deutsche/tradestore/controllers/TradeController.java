package com.deutsche.tradestore.controllers;

import com.deutsche.tradestore.models.Trade;
import com.deutsche.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/getAllTrades")
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @RequestMapping("trade/{id}")
    public Trade getTradeById(String id) {

        return tradeService.getTradeById(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/trades")
    public void addTrade(@RequestBody Trade trade) {
        tradeService.addTrade(trade);
    }
}
