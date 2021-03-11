package com.homework_21.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/operation")
public class Servlet_Operation_Manager extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        PrintWriter pw = response.getWriter();
        final int responseIndex = Integer.parseInt(request.getParameter("operationIndex"));
        switch (responseIndex){
            case (1):
                //CREATE
                response.sendRedirect("/HomeWork_Lesson_21_war_exploded/create");
                break;
            case (2):
                //UPDATE
                response.sendRedirect("/HomeWork_Lesson_21_war_exploded/update");
                break;
            case (3):
                //READ
                response.sendRedirect("/HomeWork_Lesson_21_war_exploded/read");
                break;
            case (4):
                //DELETE
                response.sendRedirect("/HomeWork_Lesson_21_war_exploded/delete");
                break;
            case (5):
                //EXIT
                break;
        }
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
         pw.println("<th> <h1> <u> <p align=\"center\"> Please, select operation: </u></h1>");
         pw.println("<form align=\"center\" method= \"post\">");
         pw.println("<select name = \"operationIndex\">");
         pw.println("<option value=\"1\"> CREATE </option>");
         pw.println("<option value=\"2\"> UPDATE </option>");
         pw.println("<option value=\"3\"> READ </option>");
         pw.println("<option value=\"4\"> DELETE </option>");
         pw.println("<option value=\"5\"> EXIT </option>");
         pw.println("</select>");
         pw.println("<input type=\"submit\" value=\"OK\">");
         pw.println("</form> ");

         pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded\">");
         pw.println("<button>Cancel</button>");
         pw.println("</form>");
         pw.println("</th>");
         pw.println("</tr>");
         pw.println("</table>");
         pw.println("</body>");
         pw.println("</html>");
    }
}
