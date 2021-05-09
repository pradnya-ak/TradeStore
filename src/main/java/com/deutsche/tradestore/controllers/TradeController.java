package com.deutsche.tradestore.controllers;

import com.deutsche.tradestore.entity.Trade;
import com.deutsche.tradestore.models.TradeDao;
import com.deutsche.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/getAllTrades")
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @RequestMapping("/trade/{id}")
    public List<Trade> getTradeById(@PathVariable String id) {
        return tradeService.getTradeListById(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/addTrade")
    public String addTrade(@RequestBody TradeDao tradeDao) {
        try{
            tradeService.addTrade(tradeDao);
        }
        catch (Exception e){
            return e.getMessage();
        }
        return "SUCCESS";
    }

    @RequestMapping("/updateExpiredFlag")
    public String updateExpiredFlag(){
        tradeService.updateExpiredFlag();
        return "SUCCESS";
    }
}
