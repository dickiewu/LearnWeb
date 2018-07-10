package name.wuxiaodong01.servlet;

import com.google.common.collect.Maps;
import name.wuxiaodong01.utils.Numbers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "StoreServlet", urlPatterns = {"/storeServlet","/system/bin/storeServlet"})
public class StoreServlet extends HttpServlet {

    private Map<Integer,String> products;
    public StoreServlet(){
        products = Maps.newHashMap();
        products.put(1,"Sandpaper");
        products.put(2,"Nails");
        products.put(3,"GLue");
        products.put(4,"Paint");
        products.put(5,"Tape");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(Objects.equals(action,"viewCart")){
            HttpSession session = request.getSession();
            Map<Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            if(cart == null ){
                cart = Maps.newHashMap();
                session.setAttribute("cart",cart);
            }
            buyProductIfPresent(request, cart);
            request.setAttribute("products",products);
            request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request,response);
        }else if(Objects.equals(action,"emptyCart")){
            HttpSession session = request.getSession();
            session.removeAttribute("cart");
            response.sendRedirect("storeServlet?action=viewCart");
        }else{
            request.setAttribute("products",products);
            request.getRequestDispatcher("/WEB-INF/jsp/productsList.jsp").forward(request,response);
        }
    }

    private void buyProductIfPresent(HttpServletRequest request, Map<Integer, Integer> cart) {
        String productIdStr = request.getParameter("productId");
        Optional<Integer> productIdOp = Numbers.parseInt(productIdStr);
        if (productIdOp.isPresent()) {
            cart.compute(productIdOp.get(),(key,oldValue)-> oldValue==null?1:++oldValue);
        }
    }
}