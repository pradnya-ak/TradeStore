package com.deutsche.tradestore.controllers;

import com.deutsche.tradestore.entity.Trade;
import com.deutsche.tradestore.models.TradeDao;
import com.deutsche.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/getAllTrades")
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @RequestMapping("trade/{id}")
    public List<Trade> getTradeById(@PathVariable String id) {
        return tradeService.getTradeListById(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/addTrade")
    public String addTrade(@RequestBody TradeDao tradeDao) {
        tradeService.addTrade(tradeDao);
        return "SUCCESS";
    }

    /*@RequestMapping(method=RequestMethod.POST, value="/updateTrade")
    public String updateTrade(@RequestBody Trade trade) {
        tradeService.addTrade(trade);
        return "SUCCESS";
    }*/

    /*@RequestMapping(method=RequestMethod.PUT, value="/updateTrade/{id}")
    public String updateTradeById(@PathVariable String id){
        tradeService.updateTradeById(id);
        return "SUCCESS";
    }*/

    /*@RequestMapping(method=RequestMethod.PUT, value="/deleteTrade/{id}")
    public String deleteTradeById(@PathVariable String id){
        tradeService.deleteTradeById(id);
        return "SUCCESS";
    }*/
}
