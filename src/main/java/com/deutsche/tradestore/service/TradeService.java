package com.deutsche.tradestore.service;

import com.deutsche.tradestore.constants.TradeConstants;
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

    /**
     * Get all the trades from database.
     * @return List<Trade>
     * */
    public List<Trade> getAllTrades(){
        List<Trade> trades = new ArrayList<>();
        tradeRepository.findAll().forEach(trades::add);
        return trades;
    }

    /**
     * Get a trade by using Trade Id from database.
     * @param tradeId A trade identification.
     * @return List<trade>
     * */
    public List<Trade> getTradeListById(String tradeId) {
        return tradeRepository.findByTradeIdOrderByVersionDesc(tradeId);
    }

    /**
     * Insert Trade into database. Also validate trade before inserting it.
     * @param tradeDao Trade object received from client.
     * @exception InvalidTradeException Exception raised for an invalid trade.
     * */
    public void addTrade(TradeDao tradeDao) throws InvalidTradeException {

        Trade tradeToBeSaved = copyPropertiesOfTrade(tradeDao);
        List<Trade> tradeList = getTradeListById(tradeToBeSaved.getTradeId());
        if(tradeList != null && tradeList.size() > 0) {
            validateVersion(tradeList, tradeToBeSaved);
        }
        validateMaturity(tradeToBeSaved);
        tradeRepository.save(tradeToBeSaved);
    }

    /**
     * Update the expired flag of trades whose maturity is expired.
     * */
    public void updateExpiredFlag(){
       for( Trade trade : getAllTrades()){
           if( trade.getMaturity().isBefore(LocalDate.now()) ){
               trade.setExpired(true);
               tradeRepository.save(trade);
           }
       }
    }

    /**
     * Validate the given trade by its version.
     * @param tradeList Trade list of a particular tradeId.
     * @param tradeToBeSaved Trade object received from client.
     * @exception InvalidTradeException Exception raised for an invalid trade.
     * */
    public void validateVersion(List<Trade> tradeList, Trade tradeToBeSaved) throws InvalidTradeException {

        if ( tradeList.get(0).getVersion() > tradeToBeSaved.getVersion() )
            throw new InvalidTradeException(TradeConstants.INVALID_VERSION);
    }

    /**
     * Validate the given trade by its maturity date.
     * @param tradeToBeSaved Trade object received from client.
     * @exception InvalidTradeException Exception raised for an invalid trade.
     * */
    public void validateMaturity(Trade tradeToBeSaved) throws InvalidTradeException {

        if( tradeToBeSaved.getMaturity().isBefore(LocalDate.now()) ){
            throw new InvalidTradeException(TradeConstants.INVALID_MATURITY);
        }
    }

    /**
     * Get a new Trade object.
     * @param tradeDao Trade object received from client.
     * @return Trade
     * */
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
