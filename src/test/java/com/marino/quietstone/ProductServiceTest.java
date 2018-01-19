package com.marino.quietstone;

import com.marino.quietstone.model.Product;
import com.marino.quietstone.model.ProductDetailsDTO;
import com.marino.quietstone.service.ProductsService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for ProductService
 *
 * @author marino
 * @since 1.0.0-SNAPSHOT
 */
public class ProductServiceTest {

    @Test
    public void getAmountIn() throws Exception {
        ProductsService productsService = new ProductsService();

        double first = productsService.getAmountIn(10.00, "USD", "EUR");
        assertEquals(6.67, first, 0.01);
        double second = productsService.getAmountIn(10.00, "USD", "AUD");
        assertEquals(14.10, second, 0.01);
    }

    @Test
    public void getProductDetails() throws Exception {
        ProductsService productsService = new ProductsService();
        ProductDetailsDTO productDetailsDTO_A2934 = productsService.getProductDetails("A2934");
        assertNotNull(productDetailsDTO_A2934);
        assertEquals(5264.10, productDetailsDTO_A2934.getTotal(), 0.01);
    }

    @Test
    public void testGetProductsList() throws Exception {
        ProductsService productsService = new ProductsService();
        List<Product> productsList = productsService.getProductsList();
        assertNotNull(productsList);
        assertFalse(productsList.isEmpty());
        assertTrue(productsList.stream().noneMatch(product -> "id".equals(product.getId())));
    }
}
