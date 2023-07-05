/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basharatkaranwalfranco.store.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author isi
 */
public class Order {
    private int id;
    private HashMap<Integer, Product> productsHashmap;
    private ArrayList<Product> productsList;
    private int customerId;
    
    public Order(int customerId) {
        this.productsList = new ArrayList<>();
        this.productsHashmap = new HashMap<>();
        this.customerId = customerId;
    }
}
