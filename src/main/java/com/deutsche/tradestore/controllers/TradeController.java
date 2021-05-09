package com.deutsche.tradestore.controllers;

import com.deutsche.tradestore.constants.TradeConstants;
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

    /**
     * Gets all the trades available in database.
     * @return List<Trade>
     * */
    @RequestMapping("/getAllTrades")
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

    /**
     * Get a trade by using Trade Id.
     * @param tradeId A trade identification.
     * @return List<trade>
     * */
    @RequestMapping("/trade/{tradeId}")
    public List<Trade> getTradeById(@PathVariable String tradeId) {
        return tradeService.getTradeListById(tradeId);
    }

    /**
     * Save Trade.
     * @param tradeDao Trade object received from client.
     * @return String
     * @exception Exception Exception raised for an invalid trade.
     * */
    @RequestMapping(method=RequestMethod.POST, value="/addTrade")
    public String addTrade(@RequestBody TradeDao tradeDao) {
        try{
            tradeService.addTrade(tradeDao);
        }
        catch (Exception e){
            return e.getMessage();
        }
        return TradeConstants.ADD_SUCCESS;
    }

    /*
    * Mark trade as expired whose maturity is expired.
    * @return String
    * */
    @RequestMapping("/updateExpiredFlag")
    public String updateExpiredFlag(){
        tradeService.updateExpiredFlag();
        return TradeConstants.JOB_SUCCESS;
    }
}
