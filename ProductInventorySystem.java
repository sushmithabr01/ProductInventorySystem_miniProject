import java.util.*;

public class ProductInventorySystem {

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, Integer> inventory = new HashMap<>();
    static HashMap<Integer, String> productNames = new HashMap<>();
    static HashMap<Integer, String> productCategories = new HashMap<>();
    static HashMap<Integer, Double> productPrices = new HashMap<>();

    static HashSet<String> categories = new HashSet<>();
    static LinkedHashSet<String> recentProducts = new LinkedHashSet<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n========================================");
            System.out.println("       PRODUCT INVENTORY SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product Quantity");
            System.out.println("5. Sell Product");
            System.out.println("6. Remove Product");
            System.out.println("7. Add Product Category");
            System.out.println("8. Display Unique Categories");
            System.out.println("9. Display Sorted Categories");
            System.out.println("10. Display Recently Added Products");
            System.out.println("11. Generate Inventory Report");
            System.out.println("12. Exit");
            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateQuantity();
                    break;
                case 5:
                    sellProduct();
                    break;
                case 6:
                    removeProduct();
                    break;
                case 7:
                    addCategory();
                    break;
                case 8:
                    displayCategories();
                    break;
                case 9:
                    displaySortedCategories();
                    break;
                case 10:
                    displayRecentProducts();
                    break;
                case 11:
                    generateReport();
                    break;
                case 12:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 12);
    }

    static void addProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.println("Product ID Already Exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        if (price < 0 || quantity < 0) {
            System.out.println("Price and Quantity Cannot Be Negative!");
            return;
        }

        inventory.put(id, quantity);
        productNames.put(id, name);
        productCategories.put(id, category);
        productPrices.put(id, price);

        categories.add(category);
        recentProducts.add(name);

        System.out.println("Product Added Successfully!");
    }

    static void displayProducts() {

        if (inventory.isEmpty()) {
            System.out.println("No Products Available!");
            return;
        }

        System.out.println("\n---------- PRODUCT INVENTORY ----------");
        System.out.println("ID\tProduct\t\tCategory\tPrice\tQuantity");

        for (Integer id : inventory.keySet()) {
            System.out.println(id + "\t" + productNames.get(id)
                    + "\t\t" + productCategories.get(id)
                    + "\t\t" + productPrices.get(id)
                    + "\t" + inventory.get(id));
        }

        System.out.println("Total Products: " + inventory.size());
    }

    static void searchProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.println("Product Name: " + productNames.get(id));
            System.out.println("Category: " + productCategories.get(id));
            System.out.println("Price: " + productPrices.get(id));
            System.out.println("Quantity: " + inventory.get(id));
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void updateQuantity() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.print("Enter New Stock Quantity: ");
            int quantity = sc.nextInt();

            if (quantity >= 0) {
                inventory.put(id, inventory.get(id) + quantity);
                System.out.println("Stock Updated Successfully!");
            } else {
                System.out.println("Quantity Cannot Be Negative!");
            }
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void sellProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {
            System.out.print("Enter Quantity Sold: ");
            int sold = sc.nextInt();

            if (sold > 0 && inventory.get(id) >= sold) {
                inventory.put(id, inventory.get(id) - sold);

                System.out.println("Sale Completed Successfully!");
                System.out.println("Product: " + productNames.get(id));
                System.out.println("Remaining Quantity: " + inventory.get(id));
            } else {
                System.out.println("Insufficient Stock or Invalid Quantity!");
            }
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void removeProduct() {

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();

        if (inventory.containsKey(id)) {

            String category = productCategories.get(id);
            String name = productNames.get(id);

            inventory.remove(id);
            productNames.remove(id);
            productPrices.remove(id);
            productCategories.remove(id);

            recentProducts.remove(name);

            boolean categoryUsed = productCategories.containsValue(category);

            if (!categoryUsed) {
                categories.remove(category);
            }

            System.out.println("Product Removed Successfully!");
        } else {
            System.out.println("Product Not Found!");
        }
    }
    static void addCategory() {

        sc.nextLine();

        System.out.print("Enter Category Name: ");
        String category = sc.nextLine();

        if (categories.add(category)) {
            System.out.println("Category Added Successfully!");
        } else {
            System.out.println("Category Already Exists!");
        }
    }

    static void displayCategories() {

        System.out.println("\n--------- PRODUCT CATEGORIES ---------");

        for (String category : categories) {
            System.out.println(category);
        }

        System.out.println("Total Unique Categories: " + categories.size());
    }

    static void displaySortedCategories() {

        TreeSet<String> sortedCategories = new TreeSet<>(categories);

        System.out.println("\n--------- SORTED CATEGORIES ----------");

        for (String category : sortedCategories) {
            System.out.println(category);
        }

        if (!sortedCategories.isEmpty()) {
            System.out.println("First Category: " + sortedCategories.first());
            System.out.println("Last Category: " + sortedCategories.last());
        }
        System.out.println("Descending Order: " + sortedCategories.descendingSet());
    }

    static void displayRecentProducts() {

        System.out.println("\n------ RECENTLY ADDED PRODUCTS ------");

        for (String name : recentProducts) {
            System.out.println(name);
        }
        System.out.println("Total Products: " + recentProducts.size());
    }

    static void generateReport() {

        int totalQuantity = 0;
        int highestQuantity = -1;
        int highestId = -1;

        System.out.println("\n--------- INVENTORY REPORT ---------");

        displayProducts();
        System.out.println("\nProducts With Quantity Below 5:");

        for (Integer id : inventory.keySet()) {
            int quantity = inventory.get(id);

            totalQuantity += quantity;

            if (quantity < 5) {
                System.out.println(productNames.get(id) + " - " + quantity);
            }

            if (quantity > highestQuantity) {
                highestQuantity = quantity;
                highestId = id;
            }
        }
        System.out.println("Total Product Units: " + totalQuantity);

        if (highestId != -1) {
            System.out.println("Highest Stock Product: "
                    + productNames.get(highestId));
            System.out.println("Highest Quantity: " + highestQuantity);
        }

        System.out.println("Unique Categories: " + categories);
        System.out.println("Total Categories: " + categories.size());
    }
}
