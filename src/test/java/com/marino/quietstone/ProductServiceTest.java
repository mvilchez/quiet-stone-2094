package com.marino.quietstone;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.service.ProductsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    private List<Rate> rates = new ArrayList<>();

    @Test
    public void getAmountIn () throws Exception
    {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
        ProductsService productsService = new ProductsService();

        double first = productsService.getAmountIn(10.00,"USD", "EUR");
        assertEquals (6.67,first,0.00);
        double second = productsService.getAmountIn(10.00,"USD", "AUD");
        assertEquals (14.10,second,0.00);
    }
}
