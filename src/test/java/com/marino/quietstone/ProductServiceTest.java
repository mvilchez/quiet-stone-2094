package com.marino.quietstone;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.service.ProductsService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    private Map<String, Rate> rates = new HashMap<String, Rate>();

    @Test
    public void getAmountIn () throws Exception
    {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
        ProductsService productsService = new ProductsService();
        double first = productsService.getAmountIn(10.00,"USD", "EUR");
        assertTrue (first==7.36);
        double second = productsService.getAmountIn(10.00,"USD", "CAD");
        assertTrue (second==10.01);
    }
}
