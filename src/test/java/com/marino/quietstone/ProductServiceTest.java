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
        assertNotNull(first);
        double second = productsService.getAmountIn(10.00, "USD", "AUD");
        assertNotNull(second);
    }

    @Test
    public void getProductDetails() throws Exception {
        ProductsService productsService = new ProductsService();
        ProductDetailsDTO productDetailsDTO_U4223 = productsService.getProductDetails("U4223");
        assertNotNull(productDetailsDTO_U4223);
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
