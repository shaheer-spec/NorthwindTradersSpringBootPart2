package com.example.NorthwindTradersSpringBootPart2;

import com.example.NorthwindTradersSpringBootPart2.models.Product;
import com.example.NorthwindTradersSpringBootPart2.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NorthwindTradersSpringBootPart2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootPart2Application.class, args);

        ProductService productService = context.getBean(ProductService.class);
        Scanner scanner = new Scanner(System.in);


        while (true){
            System.out.println("========== Ledger Application ==========");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> listAllProducts(productService);
                case 2 -> addNewProduct(scanner, productService);
                case 3 -> updateNewProduct(scanner, productService);
                case 4 -> deleteProduct(scanner, productService);
                case 5 -> searchProduct(scanner, productService);
                case 0 -> System.out.println("exiting the program");
                default -> System.out.println("Wrong input, try again.");

            }
        }
	}

    private static void listAllProducts(ProductService productService){
        System.out.println("========== List of Transactions ==========");
        List<Product> products = productService.getAllProducts();
        for (Product product1 : products) {
            System.out.println(product1);
        }
        System.out.println(" ");
    }

    private static void addNewProduct (Scanner scanner, ProductService productService){
        System.out.print("Enter a product name: ");
        String product = scanner.nextLine();

        System.out.print("Enter category ID:  ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        Product product1 = new Product(product, categoryId, unitPrice);
        Product newProduct = productService.addProduct(product1);

        System.out.println("Product Added");
        System.out.println(newProduct);
        System.out.println(" ");
    }

    private static void updateNewProduct(Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
            return;
        }

        System.out.print("Enter a product name: ");
        String product = scanner.nextLine();

        System.out.print("Enter category ID:  ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        Product product1 = new Product(product, categoryId, unitPrice);
        productService.updateProducts(productID, product1);

        System.out.println("Updated existing Product");
        System.out.println(" ");
    }

    private static void deleteProduct(Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
            return;
        }

        productService.deleteProduct(productID);
        System.out.println("Product Deleted");
    }

    private static void searchProduct (Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
        } else {
            System.out.println("========== Transaction Details ==========");
            System.out.println(checkingProduct);
            System.out.println(" ");
        }
    }
}
