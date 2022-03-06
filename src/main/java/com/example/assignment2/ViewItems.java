package com.example.assignment2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class ViewItems extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<Item, Integer> cart = (HashMap<Item, Integer>)request.getSession().getAttribute("cart");
        if(request.getParameter("item") != null) {
            int itemID = Integer.parseInt(request.getParameter("item"));
            boolean itemExistsInCart = false;

            for (Item item : cart.keySet()) {
                if (item.getItemID() == itemID) {
                    cart.put(item, cart.get(item) + 1);
                    itemExistsInCart = true;
                    break;
                }
            }

            if (itemExistsInCart == false) {
                RetrieveItems retrieveItems = new RetrieveItems();
                List<Item> itemList = retrieveItems.getItemsFromDatabase();
                Item item = new Item();

                for(int i = 0; i < itemList.size(); i++) {
                    item = itemList.get(i);
                    if(item.getItemID() == itemID)
                        cart.put(item, 1);
                }
            }
        }


        response.setContentType("text/html");

        int totalItem = 0;
        for(Item item: cart.keySet())
            totalItem += cart.get(item);

        PrintWriter out = response.getWriter();
        out.println("<h4>Items in cart: " + totalItem + "</h4>");

        RequestDispatcher rd = request.getRequestDispatcher("viewItems.jsp");
        rd.include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
