package basharatkaranwalfranco.store.repositories;

import basharatkaranwalfranco.store.models.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository
{
    private final String connectionUrl;
    private final String username;
    private final String password;
    
    public ProductRepository()
    {
        String databaseName = "store_db";
        connectionUrl = "jdbc:mariadb://localhost:3315/" + databaseName;
        username = "root";
        password = "admin";
    }
    
    public ArrayList<Product> getProductsByCategory(String category) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password))
        {
            String query = "SELECT id, name, description, category, price, inventory, img FROM products WHERE category = ? ORDER BY name";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            
            ResultSet resultSet = statement.executeQuery();
             ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next())
                products.add(readNextProduct(resultSet));
            return products;
        }
    }
    
    public Product getProductById(int productId) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password))
        {
            String query = "SELECT id, name, description, category, price, inventory, img FROM products WHERE id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            
            ResultSet resultSet = statement.executeQuery();
             ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next())
                products.add(readNextProduct(resultSet));
            return products.get(0);
        }
    }
    
    public ArrayList<Product> getProducts() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password))
        {
            String query = "SELECT id, name, description, category, price, inventory, img FROM products ORDER BY name";
            
            PreparedStatement statement = connection.prepareStatement(query);
            
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> vehicles = new ArrayList<>();
            while (resultSet.next())
                vehicles.add(readNextProduct(resultSet));
            return vehicles;
        }
    }
    
    private Product readNextProduct(ResultSet resultSet) throws SQLException
    {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        Double price = resultSet.getDouble("price");
        int inventory = resultSet.getInt("inventory");
        String imgUrl = resultSet.getString("img");
        
        return new Product(id,category,name,description,price,inventory,imgUrl);
    }
    
    public boolean updateProductInventory(int id, int quantity) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password))
        {
            String query = "UPDATE products SET inventory = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, quantity);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }
}

