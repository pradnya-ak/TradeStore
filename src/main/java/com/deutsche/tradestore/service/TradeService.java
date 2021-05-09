package com.deutsche.tradestore.service;

import com.deutsche.tradestore.entity.Trade;
import com.deutsche.tradestore.exception.InvalidTradeException;
import com.deutsche.tradestore.models.TradeDao;
import com.deutsche.tradestore.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        return tradeRepository.findByTradeIdOrderByVersionDesc(tradeId);
    }

    public void addTrade(TradeDao tradeDao) throws InvalidTradeException {

        Trade tradeToBeSaved = copyPropertiesOfTrade(tradeDao);
        List<Trade> tradeList = getTradeListById(tradeToBeSaved.getTradeId());
        if(tradeList != null && tradeList.size() > 0) {
            validateTrade(tradeList, tradeToBeSaved);
        }
        tradeRepository.save(tradeToBeSaved);
    }

    public void updateExpiredFlag(){
       for( Trade trade : getAllTrades()){
           if( trade.getMaturity().isBefore(LocalDate.now()) ){
               trade.setExpired(true);
               tradeRepository.save(trade);
           }
       }
    }

    public void validateTrade(List<Trade> tradeList, Trade tradeToBeSaved) throws InvalidTradeException {

        if ( tradeList.get(0).getVersion() > tradeToBeSaved.getVersion() )
            throw new InvalidTradeException("Lower trade version. Please use higher version trade to save it.");
        if( tradeToBeSaved.getMaturity().isBefore(LocalDate.now()) ){
            throw new InvalidTradeException("Maturity date is less than current date. Please provide date greater than current date.");
        }
    }

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
