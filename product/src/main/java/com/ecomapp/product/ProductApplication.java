package com.ecomapp.product;

import com.ecomapp.product.dto.CategoryRequest;
import com.ecomapp.product.entity.Category;
import com.ecomapp.product.entity.Product;
import com.ecomapp.product.service.CategoryService;
import com.ecomapp.product.service.ProductServiceInterf;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;
import java.util.Random;

@SpringBootApplication(
        scanBasePackages = {
                "com.ecomapp.product"
                ,"com.ecomapp.amqp"
        }
)
@EnableFeignClients(
        basePackages = {
                "com.ecomapp.feign"
        }
)
public class ProductApplication {

    @Bean
    CommandLineRunner start(ProductServiceInterf productService , CategoryService categoryService){
        return args -> {
            Random random = new Random();
              DecimalFormat df = new DecimalFormat("0.0");
            Category Phones = categoryService.save(new CategoryRequest("Phones"));
            Category laptops = categoryService.save(new CategoryRequest("Laptops"));
            Category cameras = categoryService.save(new CategoryRequest("Cameras"));
            Category headPhones = categoryService.save(new CategoryRequest("HeadPhones"));
            double randomDouble = 1 + (5 - 1) * random.nextDouble();
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Xiomi Redmi note 11").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(Phones)
                    .description(" Xiaomi Redmi Note 11 est l'un des 4 smartphones de la gamme Note 11 de Xiaomi Redmi. Il se positionne comme une version plus accessible du Redmi Note 11 5G avec un SoC Qualcomm Snapdragon 680 4G ")
                            .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .imageUrl("https://www.pngarts.com/files/8/Apple-iPhone-11-PNG-Background-Image.png")
                    .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Casques 15 max ").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(headPhones)
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .imageUrl("https://freepngimg.com/save/37089-headphones-transparent-background/600x616")
                    .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Iphone 13").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(Phones)

                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .imageUrl("https://www.citypng.com/public/uploads/preview/iphone-14-pro-and-max-deep-purple-png-11662587434zacaxkb4sd.png")
                    .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Iphone 10").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(Phones)

                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .imageUrl("https://toppng.com/uploads/preview/iphone-10-png-apple-iphone-6s-16-gb-rose-gold-unlocked-gsm-11563211430bfkjw324ip.png")
                    .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Macbook pro 13").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(laptops)
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                            .imageUrl("https://cdn0.ispace.am/AfrOrF3gWeDA6VOlDG4TzxMv39O7MXnF4CXpKUwGqRM/q:100/plain/s3://catalog-products/201111082128206860/201210170015136250.png@jpeg")
                        .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Macbook pro 12").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(Phones)
                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .imageUrl("https://png.pngitem.com/pimgs/s/133-1333773_new-mac-wallpaper-hd-hd-png-download.png").build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Iphone 13").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(Phones)
                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .imageUrl("https://www.citypng.com/public/uploads/preview/iphone-14-pro-and-max-deep-purple-png-11662587434zacaxkb4sd.png")
                    .build());
            productService.saveOrUpdateProduct(Product.builder()
                    .productTitle("Canon 250d  ").countInStock(random.nextInt(90)+10)
                    .price(random.nextInt(900)+100)
                    .category(cameras)
                    .rating(Double.parseDouble(df.format(1 + (5 - 1) * random.nextDouble())))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae odio tortor. Sed tristique libero non magna eleifend tempus. Donec dapibus condimentum eros vitae aliquet. Orci varius natoque penatibus et magnis dis parturient montes")
                    .imageUrl("https://www.pngarts.com/files/8/Canon-Camera-PNG-Transparent-Image.png")
                    .build());

        };

    }
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }


}
