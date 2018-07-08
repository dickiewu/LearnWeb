package name.wuxiaodong01.servlet;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.ByteStreams;
import name.wuxiaodong01.domain.Attachment;
import name.wuxiaodong01.domain.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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

    private volatile int id = 0;
    private Map<Integer, Ticket> ticketDatabase = Maps.newConcurrentMap();


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
                showTicketForm(response);
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
                    "inline");
            response.setContentType("text/plain");
            response.getOutputStream().write(downloadAttachment.getContents());
        }
    }

    private void showTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");
        Ticket ticket = getTicket(idString, response);
        if (ticket == null)
            return;

        PrintWriter writer = this.writeHeader(response);

        writer.append("<h2>Ticket #").append(idString)
                .append(": ").append(ticket.getSubject()).append("</h2>\r\n");
        writer.append("<i>Customer Name - ").append(ticket.getCustomerName())
                .append("</i><br/><br/>\r\n");
        writer.append(ticket.getBody()).append("<br/><br/>\r\n");

        if (ticket.getNumberOfAttachments() > 0) {
            writer.append("Attachments: ");
            int i = 0;
            for (Attachment attachment : ticket.getAttachments()) {
                if (i++ > 0)
                    writer.append(", ");
                writer.append("<a href=\"ticketServlet?action=download&ticketId=")
                        .append(idString).append("&attachment=")
                        .append(attachment.getName()).append("\">")
                        .append(attachment.getName()).append("</a>");
            }
            writer.append("<br/><br/>\r\n");
        }

        writer.append("<a href=\"ticketServlet\">Return to list tickets</a>\r\n");

        this.writeFooter(writer);

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
        PrintWriter writer = writeHeader(response);

        writer.append("<h2>Tickets</h2>\r\n");
        writer.append("<a href=\"ticketServlet?action=create\">Create Ticket")
                .append("</a><br/><br/>\r\n");

        if (ticketDatabase.size() == 0)
            writer.append("<i>There are no tickets in the system.</i>\r\n");
        else {
            for (int id : ticketDatabase.keySet()) {
                String idString = Integer.toString(id);
                Ticket ticket = ticketDatabase.get(id);
                writer.append("Ticket #").append(idString)
                        .append(": <a href=\"ticketServlet?action=view&ticketId=")
                        .append(idString).append("\">").append(ticket.getSubject())
                        .append("</a> (customer: ").append(ticket.getCustomerName())
                        .append(")<br/>\r\n");
            }
        }

        this.writeFooter(writer);
    }

    private void showTicketForm(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = writeHeader(response);

        writer.append("<h2>Create a Ticket</h2>\r\n");
        writer.append("<form method=\"POST\" action=\"ticketServlet\" ")
                .append("enctype=\"multipart/form-data\">\r\n");  // enctype= form-data
        writer.append("<input type=\"hidden\" name=\"action\" ")
                .append("value=\"create\"/>\r\n"); // 走post 方法create
        writer.append("Your Name<br/>\r\n");
        writer.append("<input type=\"text\" name=\"customerName\"/><br/><br/>\r\n");
        writer.append("Subject<br/>\r\n");
        writer.append("<input type=\"text\" name=\"subject\"/><br/><br/>\r\n");
        writer.append("Body<br/>\r\n");
        writer.append("<textarea name=\"body\" rows=\"5\" cols=\"30\">")
                .append("</textarea><br/><br/>\r\n");
        writer.append("<b>Attachments</b><br/>\r\n");
        writer.append("<input type=\"file\" name=\"file1\"/><br/><br/>\r\n");
        writer.append("<img name=\"pic\" value=\"picutreeee\"/><br/><br/>\r\n");
        writer.append("<input type=\"submit\" value=\"Submit\"/>\r\n");
        writer.append("</form>\r\n");

        this.writeFooter(writer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("action is :" + action);
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
        Ticket ticket = new Ticket();
        ticket.setCustomerName(req.getParameter("customerName"));
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
        System.out.println("redirect path is :" + redirectPath);
        resp.sendRedirect(redirectPath);
    }

    private Attachment processAttachment(Part file1) throws IOException {
        String submittedFileName = file1.getSubmittedFileName();
        System.out.println("submittedFIleName:" + submittedFileName);
        long size = file1.getSize();
        System.out.println("size is :" + size);
        String contentType = file1.getContentType();
        System.out.println("contentType:" + contentType);
        Collection<String> headerNames = file1.getHeaderNames();
        headerNames.forEach(headerName -> {
            String header = file1.getHeader(headerName);
            System.out.println("header:" + headerName + ",value:" + header);
        });
        Attachment attachment = new Attachment();
        byte[] contents = ByteStreams.toByteArray(file1.getInputStream());
        attachment.setContents(contents);
        attachment.setName(file1.getSubmittedFileName());
        return attachment;
    }

    private void listTickets(HttpServletResponse response)
            throws ServletException, IOException {

    }


    private PrintWriter writeHeader(HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("    <head>\r\n")
                .append("        <title>Customer Support</title>\r\n")
                .append("    </head>\r\n")
                .append("    <body>\r\n");

        return writer;
    }

    private void writeFooter(PrintWriter writer) {
        writer.append("    </body>\r\n").append("</html>\r\n");
    }
}