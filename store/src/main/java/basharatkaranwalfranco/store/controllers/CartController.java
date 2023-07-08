/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basharatkaranwalfranco.store.controllers;

import basharatkaranwalfranco.store.models.Cart;
import basharatkaranwalfranco.store.models.Product;
import basharatkaranwalfranco.store.repositories.ProductRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "CartController", urlPatterns = {"/cart", ""})
public class CartController extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the cart from the session
        Cart cart = getCartFromSession(request.getSession());
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the action and product ID from the request parameters
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Retrieve the cart from the session
        Cart cart = getCartFromSession(request.getSession());

        if (action.equals("add")) {
            // Retrieve the product details from the request parameters
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            try {
                Product product = productRepository.getProductById(productId);
                Cart cartItem = new Cart();
                cart.addProductUnit(cartItem);

                // Set the success message as an attribute in the request
                request.setAttribute("message", "Product added to cart successfully");
            } catch (ClassNotFoundException | SQLException e) {
                request.setAttribute("error", "Failed to add the product to cart");
            }
        } else if (action.equals("removeProduct")) {
            // Remove the product from the cart
            cart.removeProduct(cartItem);

            // Set the success message as an attribute in the request
            request.setAttribute("message", "Product removed from cart successfully");
        }else if (action.equals("removeUnit")) {
            // Retrieve the product from the cart
            Product product = cart.getProductById(productId);

            // Remove one unit from the product quantity in the cart
            cart.removeProductUnit(product);

            // Set the success message as an attribute in the request
            request.setAttribute("message", "Product unit removed successfully");
        }

        // Forward to the cart.jsp
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    }

    private Cart getCartFromSession(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

}
