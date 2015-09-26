package com.schneider_electric.dces.pricing.service;

import com.schneider_electric.dces.pricing.dao.PriceDao;
import com.schneider_electric.dces.pricing.model.PriceList;
import com.schneider_electric.dces.pricing.model.PriceListType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.NotAcceptableException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {

	@Mock
	private PriceDao priceDao;

	@InjectMocks
	private PriceServiceImpl priceService;

    @Test(expected=NotAcceptableException.class)
    public void cant_change_pricelist_type(){
        when(priceDao.getPriceList("federatedId","priceListId")).thenReturn(new PriceList("id","name","description", PriceListType.PUBLIC,"EUR"));
        priceService.updatePriceList("federatedId",new PriceList("id","name","description", PriceListType.USER,"EUR"),"priceListId");
    }

}
