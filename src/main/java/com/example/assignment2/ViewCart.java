package com.example.assignment2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ViewCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<Item, Integer> cart = (HashMap<Item, Integer>)request.getSession().getAttribute("cart");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int incrementItemID;
        int decrementItemID;
        int removeItemID;

        if(request.getParameter("increment") != null) {
            incrementItemID = Integer.parseInt(request.getParameter("increment"));
            for (Item item : cart.keySet()) {
                if (item.getItemID() == incrementItemID) {
                    cart.put(item, cart.get(item) + 1);
                    break;
                }
            }
        }

        if(request.getParameter("decrement") != null) {
            decrementItemID = Integer.parseInt(request.getParameter("decrement"));
            for (Item item : cart.keySet()) {
                if (item.getItemID() == decrementItemID && cart.get(item) != 1) {
                    cart.put(item, cart.get(item) - 1);
                    break;
                }
            }
        }

        if(request.getParameter("remove") != null) {
            removeItemID = Integer.parseInt(request.getParameter("remove"));
            for (Item item : cart.keySet()) {
                if (item.getItemID() == removeItemID) {
                    cart.remove(item);
                    break;
                }
            }
        }


        if(cart.size() == 0)
            out.println("Your cart is empty.");

        out.println("<form action=\"ViewItems\"><button>Homepage</button></form>");
        out.println("<form action=\"Checkout\" method=\"post\">\n<button>Checkout</button></form><br>");

        for(Item item: cart.keySet())
        {
            out.println(item.getItemName() + " Quantity: " + cart.get(item) + "<br>");
            out.println("<img src=\"" + request.getContextPath() + item.getItemImage() + "\" width=\"100\"/><br>");
            out.println("<form action=\"ViewCart\">\n" +
                    "    <button name=\"decrement\" value=\"" + item.getItemID() + "\">-</button>" +
                    "    <button name=\"increment\" value=\"" + item.getItemID() + "\">+</button>" +
                    "    <button name=\"remove\" value=\"" + item.getItemID() + "\">Remove Item</button>" +
                    "</form><br><br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
