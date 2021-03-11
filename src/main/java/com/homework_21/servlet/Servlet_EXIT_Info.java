package com.homework_21.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/exit")
public class Servlet_EXIT_Info extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 10; i++) {
            pw.println("<br>");
        }
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th> <h1> <u> <p align=\"center\" style=\"color:#0000ff\"> Thank you for using our system: </u></h1>");
        pw.println("</th>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 10; i++) {
            pw.println("<br>");
        }
        pw.println("<th> <h6> <u> <p align=\"right\"> All rights reserved </u></h6>");
        pw.println("<th> <h6> <u> <p align=\"right\"> Garmilin Corporation 2021 </u></h6>");
        pw.println("</body>");
        pw.println("</html>");
    }
}
