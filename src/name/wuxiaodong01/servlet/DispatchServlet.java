package name.wuxiaodong01.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatchServlet", urlPatterns = {"/dispatchServlet"})
public class DispatchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dispatch servlet received info");
        System.out.println("req:"+request+",response:"+response);
        System.out.println(Thread.currentThread().getName()+"--"+ Thread.currentThread().getId());

    }
}