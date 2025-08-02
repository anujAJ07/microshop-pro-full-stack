package com.example.productservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is already populated
        if (productRepository.count() == 0) {
            loadProducts();
        }
    }

    private void loadProducts() {
        List<Product> products = List.of(
            new Product(null, "Espresso Machine", 499.99, "A premium espresso machine for the perfect home-brewed coffee.", "Appliances", 
                List.of("https://images.unsplash.com/photo-1565451286022-7238b72a8f37", "https://images.unsplash.com/photo-1559239115-ce3f46a01382", "https://images.unsplash.com/photo-1621873495945-85635c43b006")),
            
            new Product(null, "Wireless Headphones", 199.50, "Noise-cancelling over-ear headphones with superior sound quality.", "Electronics", 
                List.of("https://images.unsplash.com/photo-1505740420928-5e560c06d30e", "https://images.unsplash.com/photo-1546435770-a3e426bf4022", "https://images.unsplash.com/photo-1613040809024-b4ef7ba99bc3")),

            new Product(null, "Leather Backpack", 120.00, "A stylish and durable leather backpack, perfect for work or travel.", "Fashion", 
                List.of("https://images.unsplash.com/photo-1553062407-98eeb68c6a62", "https://images.unsplash.com/photo-1587351601628-a28233c3cafb", "https://images.unsplash.com/photo-1599399432685-644a7393410a")),

            new Product(null, "Smart Watch", 299.00, "Track your fitness and stay connected with this sleek smart watch.", "Electronics", 
                List.of("https://images.unsplash.com/photo-1546868871-7041f2a55e12", "https://images.unsplash.com/photo-1523170335258-f5ed11844a49", "https://images.unsplash.com/photo-1579586337278-35d99a4a822d")),

            new Product(null, "Yoga Mat", 45.00, "Eco-friendly, non-slip yoga mat for your daily practice.", "Sports", 
                List.of("https://images.unsplash.com/photo-1591291621164-2c6367723315", "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b", "https://images.unsplash.com/photo-1599447462453-65b613745645")),
            
            new Product(null, "Hardcover Journal", 22.50, "A beautiful hardcover journal with 200 lined pages.", "Stationery", 
                List.of("https://images.unsplash.com/photo-1516414447565-b14be0adf13e", "https://images.unsplash.com/photo-1484480974693-6ca0a78fb36b", "https://images.unsplash.com/photo-1456735190827-d1262f71b8a3")),

            new Product(null, "Ceramic Mug", 18.00, "A handcrafted ceramic mug, perfect for your morning coffee or tea.", "Homeware", 
                List.of("https://images.unsplash.com/photo-1541167760496-1628856ab772", "https://images.unsplash.com/photo-1510626419130-531254247496", "https://images.unsplash.com/photo-1608682513994-e8b39119561b")),

            new Product(null, "Desk Lamp", 75.00, "A modern LED desk lamp with adjustable brightness and color temperature.", "Homeware", 
                List.of("https://images.unsplash.com/photo-1507413245164-6160d8298b31", "https://images.unsplash.com/photo-1617092562282-b5bf172a6b1a", "https://images.unsplash.com/photo-1543297059-44438c82a781")),
            
            new Product(null, "Running Shoes", 135.00, "Lightweight and responsive running shoes for peak performance.", "Sports", 
                List.of("https://images.unsplash.com/photo-1542291026-7eec264c27ff", "https://images.unsplash.com/photo-1460353581641-37baddab0fa2", "https://images.unsplash.com/photo-1595950653106-60904f39ce09")),
                
            new Product(null, "Sunglasses", 85.00, "Classic aviator sunglasses with polarized lenses for UV protection.", "Fashion", 
                List.of("https://images.unsplash.com/photo-1511499767150-a48a237f0083", "https://images.unsplash.com/photo-1577803645773-40491054aaaa", "https://images.unsplash.com/photo-1552346154-21d32810aba3"))
        );
        productRepository.saveAll(products);
        System.out.println("Loaded " + products.size() + " products into the database.");
    }
}
