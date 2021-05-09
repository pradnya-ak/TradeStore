package com.deutsche.tradestore.service;

import com.deutsche.tradestore.entity.Trade;
import com.deutsche.tradestore.models.TradeDao;
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

    public List<Trade> getTradeListById(String tradeId) {
        return tradeRepository.findByTradeId(tradeId);
    }

    public void addTrade(TradeDao tradeDao){
        Trade tradeToBeSaved = copyPropertiesOfTrade(tradeDao);
        List<Trade> tradeList = getTradeListById(tradeToBeSaved.getTradeId());
        if(tradeList != null && tradeList.size() > 0){
            //update
            validateTrade(tradeList);
        }
        else{
            //save
        }
        tradeRepository.save(tradeToBeSaved);
    }

    /*public String deleteTradeById(String tradeId){
        tradeRepository.deleteById(tradeId);
        return "SUCCESS";
    }*/

    public void validateTrade(List<Trade> tradeList){

    }

    /*public void tradeExists(String tradeId){
        getTradeById()
    }*/

    public Trade copyPropertiesOfTrade(TradeDao tradeDao){
        return new Trade(tradeDao.getTradeId(),
                        tradeDao.getVersion(),
                        tradeDao.getCounterPartyId(),
                        tradeDao.getBookId(),
                        tradeDao.getMaturity(),
                        tradeDao.getCreatedDate(),
                        tradeDao.isExpired());
    }

}
