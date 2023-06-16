package basharatkaranwalfranco.store.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

    private HashMap<Integer, Product> productsHashmap;
    private ArrayList<Product> productsList;
    private final Double tax = 0.15;

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public Cart() {
        this.productsList = new ArrayList<>();
        this.productsHashmap = new HashMap<>();
    }

    /*
    public void addProductUnit(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            if (product.getQuantity() < product.getInventory()) {
                product.setQuantity(product.getQuantity()++);
            }
        } else {
            if (product.getInventory() >= 1) {
                product.setQuantity(1);
                Products.Add(product);
                productsDictionary.Add(product.Id, product);
            }
        }
    }
     */
    //TODO: Add inventory validation in Controller
    //Verify is there enough inventory to add a new item
    //and also that the current amount in the cart is less than the total inventory
    public void addProductUnit(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            product.setQuantity(product.getQuantity()++);
        } else {

            product.setQuantity(1);
            productsList.add(product);
            productsHashmap.put(product.getId(), product);

        }
    }

    public void removeProductUnit(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity()--);
            } else {
                product.setQuantity(0);
                productsList.remove(product);
                productsHashmap.remove(product.getId());
            }
        }
    }

    public void removeProduct(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            product.setQuantity(0);
            productsList.remove(product);
            productsHashmap.remove(product.getId());
        }
    }

    public void EmptyCart() {
        foreach(Product product in Products
        )
                product.Quantity = 0;

        Products.Clear();
        productsDictionary.Clear();
    }

    public decimal Subtotal

    {
        get
        {
            return productsDictionary.Sum(x =  > x.Value.TotalPrice);
        }
    }

    public decimal TaxAmount

    {
        get
        {
            return Subtotal * Tax;
        }
    }

    public decimal Total

    {
        get
        {
            return Subtotal + TaxAmount;
        }
    }
}
}
