package com.marino.quietstone;

import com.marino.quietstone.load.LoadRates;
import com.marino.quietstone.load.LoadTransactions;
import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetailsDTO;
import com.marino.quietstone.model.Rate;
import com.marino.quietstone.model.Transaction;
import com.marino.quietstone.service.ProductsService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ProductServiceTest {
    private List<Rate> rates = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    @Test
    public void getAmountIn() throws Exception {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
        ProductsService productsService = new ProductsService();

        double first = productsService.getAmountIn(10.00, "USD", "EUR");
        assertEquals(6.67, first, 0.01);
        double second = productsService.getAmountIn(10.00, "USD", "AUD");
        assertEquals(14.10, second, 0.01);
    }

    @Test
    public void getProductDetails() throws Exception {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
        LoadTransactions loadTransactions = new LoadTransactions();
        transactions = loadTransactions.loadTransactions();
        ProductsService productsService = new ProductsService();
        ProductDetailsDTO productDetailsDTO_A2934 = productsService.getProductDetails("A2934");
        assertNotNull(productDetailsDTO_A2934);
        assertEquals(200.0, productDetailsDTO_A2934.getTotal(), 0.01);
    }

    @Test
    public void testGetProductsList() throws Exception {
        LoadRates loadRates = new LoadRates();
        rates = loadRates.loadRates();
        LoadTransactions loadTransactions = new LoadTransactions();
        transactions = loadTransactions.loadTransactions();
        ProductsService productsService = new ProductsService();
        List<Product> listaProductos = productsService.getProductsList();
        assertNotNull(listaProductos);
        assertTrue(listaProductos.isEmpty());
        System.out.println("Lista de duplicados "+ listaProductos.stream().distinct().map(Product::getId)
                .collect(Collectors.joining(",")));
        assertTrue(listaProductos.stream().anyMatch(product -> "id".equals(product.getId())));
    }
}
