package com.deutsche.testcase;


import com.deutsche.tradestore.entity.Trade;
import com.deutsche.tradestore.exception.InvalidTradeException;
import com.deutsche.tradestore.models.TradeDao;
import com.deutsche.tradestore.service.TradeService;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeStoreTest {

    @Autowired
    TradeService  tradeService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void lowerVersionTest() throws InvalidTradeException {
        TradeDao tradeDao = new TradeDao("T2", 0, "CP-3", "B1", LocalDate.of(2021,5,30),
                LocalDate.of(2021,5,10), false);
        tradeService.addTrade(tradeDao);

    }
}
