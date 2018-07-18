package name.wuxiaodong01.servlet;

import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import name.wuxiaodong01.domain.Attachment;
import name.wuxiaodong01.domain.Ticket;
import name.wuxiaodong01.utils.Strings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

@WebServlet(
        name = "ticketServlet",
        urlPatterns = {"/ticketServlet"}
)

@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
)
public class TicketServlet extends HttpServlet {

    private Map<Integer, Ticket> ticketDatabase = Maps.newConcurrentMap();

    private volatile int id = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (Strings.isNullOrEmpty(action)) {
            action = "list";
        }
        switch (action) {
            case "create":
                showTicketForm(request, response);
                break;
            case "view":
                showTicket(request, response);
                break;
            case "download":
                processDownload(request,response);
                break;
            case "list":
                showTicketsLists(request, response);
                break;
        }
    }


    private void processDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketId = request.getParameter("ticketId");
        String attachment = request.getParameter("attachment");
        Ticket ticket = getTicket(ticketId,response);
        if(ticket !=null){
            Attachment downloadAttachment = ticket.getAttachment(attachment);
            response.setHeader("Content-Disposition",
                    "attachment;filename="+URLEncoder.encode(attachment,"utf-8"));
            response.setContentType("text/plain");
            response.getOutputStream().write(downloadAttachment.getContents());
        }
    }

    private void showTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketId = request.getParameter("ticketId");
        Ticket ticket = getTicket(ticketId, response);
        if (ticket == null)
            return;
        request.setAttribute("ticket",ticket);
        request.setAttribute("ticketId",ticketId);
        request.getRequestDispatcher("/WEB-INF/jsp/viewTicket.jsp").forward(request,response);
    }

    private Ticket getTicket(String idString, HttpServletResponse response)
            throws ServletException, IOException {
        if (idString == null || idString.length() == 0) {
            response.sendRedirect("tickets");
            return null;
        }

        try {
            Ticket ticket = this.ticketDatabase.get(Integer.parseInt(idString));
            if (ticket == null) {
                response.sendRedirect("tickets");
                return null;
            }
            return ticket;
        } catch (Exception e) {
            response.sendRedirect("tickets");
            return null;
        }
    }

    private void showTicketsLists(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        req.setAttribute("ticketDb",ticketDatabase);
        req.getRequestDispatcher("/WEB-INF/jsp/ticketList.jsp").forward(req,response);
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/showForm.jsp");
        requestDispatcher.forward(request,response);
        System.out.println("after forward, response commited:"+response.isCommitted());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (Strings.isNullOrEmpty(action)) {
            action = "list";
        }

        switch (action) {
            case "create":
                doCreate(req, resp);
                break;
            default:
                break;
        }
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        Ticket ticket = new Ticket();
        String username= (String) req.getSession().getAttribute("username");
        ticket.setCustomerName(username);
        ticket.setBody(req.getParameter("body"));
        ticket.setSubject(req.getParameter("subject"));

        Part file1 = req.getPart("file1");
        Attachment attachment = null;
        if (file1 != null) {
            attachment = processAttachment(file1);
            ticket.addAttachment(attachment);
            ticketDatabase.put(id++, ticket);
        }

        String redirectPath = String.format(Locale.ENGLISH, "%s%s%s", getServletContext().getContextPath(), "/ticketServlet", "?action=list");
        resp.sendRedirect(redirectPath);
    }

    private Attachment processAttachment(Part file1) throws IOException {
        Attachment attachment = new Attachment();
        byte[] contents = ByteStreams.toByteArray(file1.getInputStream());
        attachment.setContents(contents);
        attachment.setName(file1.getSubmittedFileName());
        return attachment;
    }
}



















