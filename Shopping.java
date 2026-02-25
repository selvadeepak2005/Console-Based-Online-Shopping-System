import java.util.*;

class Client {
    String name;
    String role;   
    int id;
    int pin;

    Client(String name, String role, int id, int pin) {
        this.name = name;
        this.role = role;
        this.id = id;
        this.pin = pin;
    }
}

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class Shopping {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Client> users = new ArrayList<>();
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Product> cart = new ArrayList<>();

    static int userIdCounter = 0;
    static int productIdCounter = 0;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n------ ONLINE SHOP ------");
            System.out.println("1. Seller");
            System.out.println("2. Buyer");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: seller(); break;
                case 2: buyer(); break;
                case 0: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

   
    static void seller() {
        System.out.println("\n1.Register");
        System.out.println("2.Login");
        System.out.println("3.Back");

        int ch = sc.nextInt();

        switch (ch) {
            case 1: register("seller"); break;
            case 2:
                Client s = login("seller");
                if (s != null) sellerMenu();
                break;
            case 3: return;
            default: System.out.println("Invalid choice");
        }
    }

    static void sellerMenu() {
        while (true) {
            System.out.println("\n--- SELLER DASHBOARD ---");
            System.out.println("1.Add Product");
            System.out.println("2.Update Product");
            System.out.println("3.Delete Product");
            System.out.println("4.View Products");
            System.out.println("5.Logout");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addProduct(); break;
                case 2: updateProduct(); break;
                case 3: deleteProduct(); break;
                case 4: viewProducts(); break;
                case 5: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

   
    static void buyer() {
        System.out.println("\n1.Register");
        System.out.println("2.Login");
        System.out.println("3.Back");

        int ch = sc.nextInt();

        switch (ch) {
            case 1: register("buyer"); break;
            case 2:
                Client b = login("buyer");
                if (b != null) buyerMenu();
                break;
            case 3: return;
            default: System.out.println("Invalid choice");
        }
    }

    static void buyerMenu() {
        while (true) {
            System.out.println("\n--- BUYER DASHBOARD ---");
            System.out.println("1.View Products");
            System.out.println("2.Add to Cart");
            System.out.println("3.View Cart & Buy");
            System.out.println("4.Logout");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: viewProducts(); break;
                case 2: addToCart(); break;
                case 3: buy(); break;
                case 4: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

   
    static void register(String role) {
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        int id = ++userIdCounter;

        users.add(new Client(name, role, id, pin));

        System.out.println("Registered Successfully!");
        System.out.println("Your ID: " + id);
    }

    static Client login(String role) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        for (Client c : users) {
            if (c.id == id && c.pin == pin && c.role.equals(role)) {
                System.out.println("Login Successful!");
                return c;
            }
        }

        System.out.println("Invalid Credentials!");
        return null;
    }

    
    static void addProduct() {
        sc.nextLine();
        System.out.print("Product Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        int id = ++productIdCounter;

        products.add(new Product(id, name, price));

        System.out.println("Product Added! ID: " + id);
    }

    static void updateProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for (Product p : products) {
            if (p.id == id) {
                sc.nextLine();
                System.out.print("New Price: ");
                p.price = sc.nextDouble();

                System.out.println("Updated Successfully!");
                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    static void deleteProduct() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        Iterator<Product> it = products.iterator();

        while (it.hasNext()) {
            Product p = it.next();
            if (p.id == id) {
                it.remove();
                System.out.println("Deleted Successfully!");
                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No Products Available!");
            return;
        }

        for (Product p : products) {
            System.out.println("ID: " + p.id + " | " + p.name + " | ₹" + p.price);
        }
    }

    static void addToCart() {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        for (Product p : products) {
            if (p.id == id) {
                cart.add(p);
                System.out.println("Added to Cart!");
                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    static void buy() {
        if (cart.isEmpty()) {
            System.out.println("Cart is Empty!");
            return;
        }

        double total = 0;

        System.out.println("\n--- BILL ---");
        for (Product p : cart) {
            System.out.println(p.name + " - ₹" + p.price);
            total += p.price;
        }

        System.out.println("Total: ₹" + total);
        cart.clear();
        System.out.println("Purchase Successful!");
    }
}