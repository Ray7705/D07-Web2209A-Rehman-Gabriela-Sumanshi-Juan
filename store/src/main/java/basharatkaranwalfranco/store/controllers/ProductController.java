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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String category = request.getParameter("category");
            ArrayList<Product> products;
            ProductRepository repository = new ProductRepository();

            //if category parameter comes retrieve products by category
            if (category != null || !"all".equals(category)) {
                products = repository.getProductsByCategory(category);
            } else {
                //else retrieve all prods
                products = repository.getProducts();

            }

            //TODO -> En el jsp colocar tabs con las distintas categorias. Son anchors y por lo tanto
            //haran un trigger de un get nuevamente
            //request.setAttribute("category", vehicles);
            //request.getRequestDispatcher("WEB-INF/vehicles.jsp").forward(request, response);
            //Obtaining cart and updating product quantities
            HttpSession session = request.getSession(true);
            Cart cartInSession = (Cart) session.getAttribute("cart");
            if (cartInSession != null) {
                HashMap<Integer, Product> productsHashMap = cartInSession.getProductsHashMap();
                int counter = 0;
                for (Product product : products) {
                    if (productsHashMap.containsKey(product.getId())) {
                        product.setQuantity(productsHashMap.get(product.getId()).getQuantity());
                        products.set(counter++, product);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO -> Es necesario encerrar el boton de mas y de menos en una etiqueta form para que se vaya
        //como post
        //TODO-> Mandar parametro diff dependiendo si es increase o decrease product

        try {
            String actionParam = request.getParameter("action");
            String productId = request.getParameter("productId");
            ProductRepository repository = new ProductRepository();
            Product product = repository.getProductById(Integer.parseInt(productId));
            
            //retrieving cart. If it doesnt exist it will be created.
            HttpSession session = request.getSession(true);
            Cart cartInSession = (Cart) session.getAttribute("cart");
            if (cartInSession == null) {
                cartInSession = new Cart();
            }
            if ("plus".equals(actionParam)) {
                cartInSession.addProductUnit(product);

            } else if ("minus".equals(actionParam)) {
                cartInSession.removeProductUnit(product);
            }
            
            //save updated cart into session
            session.setAttribute("cart", cartInSession);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
