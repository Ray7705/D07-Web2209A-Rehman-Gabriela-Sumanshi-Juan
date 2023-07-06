package basharatkaranwalfranco.store.controllers;

import basharatkaranwalfranco.store.models.Cart;
import basharatkaranwalfranco.store.models.Product;
import basharatkaranwalfranco.store.repositories.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ProductController", urlPatterns = {"/products", ""})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Saving category in session
            HttpSession session = request.getSession(true);
            String category = request.getParameter("category");
            if (category == null) {
                category = "all";
            }

            ArrayList<Product> products;
            ProductRepository repository = new ProductRepository();

            //if category parameter does not come, show all products
            if ("all".equals(category)) {
                products = repository.getProducts();
            } else {
                products = repository.getProductsByCategory(category);
            }

            //Obtaining cart and updating product quantities
            Cart cartInSession = (Cart) session.getAttribute("cart");
            if (cartInSession != null) {
                HashMap<Integer, Product> productsHashMap = cartInSession.getProductsHashMap();
                for (Product product : products) {
                    if (productsHashMap.containsKey(product.getId())) {
                        Product cartProduct = productsHashMap.get(product.getId());
                        int quantity = cartProduct.getQuantity();
                        quantity = Math.min(quantity, product.getInventory());
                        product.setQuantity(quantity);
                        cartProduct.setQuantity(quantity);
                        cartProduct.setInventory(product.getInventory());
                    }
                }
            }

            //Sending parameters
            request.setAttribute("products", products);
            request.setAttribute("category", category);
            request.getRequestDispatcher("WEB-INF/productlist.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //getting parameters
            String actionParam = request.getParameter("action");
            String productId = request.getParameter("productId");
            String category = request.getParameter("category");
            if (category == null) {
                category = "all";
            }

            int id = Integer.parseInt(productId);
            ProductRepository repository = new ProductRepository();
            Product productById = repository.getProductById(id);

            //retrieving cart. If it doesnt exist it will be created.
            HttpSession session = request.getSession(true);
            Cart cartInSession = (Cart) session.getAttribute("cart");
            if (cartInSession == null) {
                cartInSession = new Cart();
            }

            //defining action depending on the button selected
            if ("plus".equals(actionParam)) {
                int quantity = cartInSession.getQuantity(id) + 1;
                quantity = Math.min(quantity, productById.getInventory());
                productById.setQuantity(quantity);
                cartInSession.setQuantity(productById);
            } else if ("minus".equals(actionParam)) {
                int quantity = cartInSession.getQuantity(id) - 1;
                quantity = Math.min(quantity, productById.getInventory());
                productById.setQuantity(quantity);
                cartInSession.setQuantity(productById);
            }

            //save updated cart into session
            session.setAttribute("cart", cartInSession);

            ArrayList<Product> products;

            //if category parameter does not come, show all products
            if ("all".equals(category)) {
                products = repository.getProducts();
            } else {
                products = repository.getProductsByCategory(category);
            }

            //Get product list from cart and updating product quantities
            HashMap<Integer, Product> productsHashMap = cartInSession.getProductsHashMap();

            for (Product product : products) {
                if (productsHashMap.containsKey(product.getId())) {
                    Product cartProduct = productsHashMap.get(product.getId());
                    int quantity = cartProduct.getQuantity();
                    quantity = Math.min(quantity, product.getInventory());
                    product.setQuantity(quantity);
                    cartProduct.setQuantity(quantity);
                    cartProduct.setInventory(product.getInventory());
                }
            }

            request.setAttribute("cart", cartInSession);
            request.setAttribute("products", products);
            request.setAttribute("category", category);
            request.getRequestDispatcher("WEB-INF/productlist.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
