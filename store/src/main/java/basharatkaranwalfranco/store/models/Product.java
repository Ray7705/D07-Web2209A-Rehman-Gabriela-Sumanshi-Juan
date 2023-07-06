package basharatkaranwalfranco.store.models;

public class Product {

    private int id;
    private String category;
    private String name;
    private String description;

    private double price; 
    private int quantity;

    private int inventory;
    public String imageUrl;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //TODO -> Define if it's better to have it private or public
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

  
    public Product(int id, String category, String name, String description, Double price, int quantity, int inventory, String imageUrl) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.inventory = inventory;
    }

    public Product(int id, String category, String name, String description, Double price, int inventory, String imageUrl) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = 0;
        this.imageUrl = imageUrl;
        this.inventory = inventory;
    }
    
     public double getTotalPrice() {
        return price * quantity;
    }
}
