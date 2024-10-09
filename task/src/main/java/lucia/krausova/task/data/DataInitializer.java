//package lucia.krausova.task.data;
//
//import jakarta.annotation.PostConstruct;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lucia.krausova.task.entities.Product;
//import lucia.krausova.task.repositories.CategoryRepository;
//import lucia.krausova.task.repositories.ProductRepository;
//import org.hibernate.annotations.Comment;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements InitializingBean {
//
//    private CategoryRepository categoryRepository;
//    private ProductRepository productRepository;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//    //    loadData();
//    }
//
//
//    public void loadData() {
//
//        Product apple = Product.builder()
//                .name("apple")
//                .price(new BigDecimal("0.12"))
//                .build();
//        Product orange = Product.builder()
//                .name("orange")
//                .price(new BigDecimal("0.09"))
//                .build();
//
//        List<Product> products = new ArrayList<>();
//        products.add(apple);
//        products.add(orange);
//
//        productRepository.save(apple);
//        productRepository.save(orange);
//
//    }
//
//}
