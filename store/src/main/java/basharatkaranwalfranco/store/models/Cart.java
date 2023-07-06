package basharatkaranwalfranco.store.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private HashMap<Integer, Product> productsHashmap;
    private ArrayList<Product> productsList;
    private final double tax = 0.15;

    public Cart() {
        this.productsList = new ArrayList<>();
        this.productsHashmap = new HashMap<>();
    }

    public ArrayList<Product> getProductList() {
        return productsList;
    }

    public HashMap<Integer, Product> getProductsHashMap() {
        return productsHashmap;
    }

    public int getQuantity(int productId) {
        Product product = productsHashmap.get(productId);
        if (product == null) {
            return 0;
        }
        return product.getQuantity();
    }

    public void setQuantity(Product product) {
        int id = product.getId();
        if (product.getQuantity() >= 1) {
            if (productsHashmap.containsKey(id)) {
                Product cartProduct = productsHashmap.get(id);
                cartProduct.setQuantity(product.getQuantity());
            } else {
                productsList.add(product);
                productsHashmap.put(product.getId(), product);
            }
        } else {
            removeProduct(id);
        }
    }

    private void removeProduct(int id) {
        if (productsHashmap.containsKey(id)) {
            productsHashmap.remove(id);
            for (int i = 0; i < productsList.size(); i++) {
                Product product = productsList.get(i);
                if (product.getId() == id) {
                    productsList.remove(i);
                    break;
                }
            }
        }
    }

    public void addProductUnit(Product product) {
        int id = product.getId();
        if (productsHashmap.containsKey(id)) {
            product = productsHashmap.get(id);
            product.setQuantity(product.getQuantity() + 1);
        } else {
            product.setQuantity(1);
            productsList.add(product);
            productsHashmap.put(product.getId(), product);
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

    public double getSubtotal() {

        double sum = 0d;
        for (Map.Entry<Integer, Product> entry : productsHashmap.entrySet()) {
            sum += entry.getValue().getTotalPrice();
        }

        return sum;

    }

    public double getTaxAmount() {

        return getSubtotal() * tax;

    }

    public double getTotal() {

        return getSubtotal() + getTaxAmount();
    }

}
