package basharatkaranwalfranco.store.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private HashMap<Integer, Product> productsHashmap;
    private ArrayList<Product> productsList;
    private final Double tax = 0.15;

    public Cart() {
        this.productsList = new ArrayList<>();
        this.productsHashmap = new HashMap<>();
    }

    public ArrayList<Product> getProductList() {
        return productsList;
    }

    public void addProductUnit(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            if (product.getQuantity() < product.getInventory()) {
                product.setQuantity(product.getQuantity() + 1);
            }
        } else {
            if (product.getInventory() >= 1) {

                product.setQuantity(1);
                productsList.add(product);
                productsHashmap.put(product.getId(), product);
            }
        }
    }

    public void removeProductUnit(Product product) {
        if (productsHashmap.containsKey(product.getId())) {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
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

    public void emptyCart() {
        for (Product product : productsList) {
            product.setQuantity(0);

            productsList.clear();
            productsHashmap.clear();
        }
    }

    public Double getSubtotal() {
        
        Double sum =0d;
        for (Map.Entry<Integer, Product> entry : productsHashmap.entrySet()) {
            sum += entry.getValue().getTotalPrice();
        }

        return sum; 

    }

    public Double getTaxAmount() {

        return getSubtotal() * tax;

    }

    public Double getTotal() {

        return getSubtotal() + getTaxAmount();
    }

}
