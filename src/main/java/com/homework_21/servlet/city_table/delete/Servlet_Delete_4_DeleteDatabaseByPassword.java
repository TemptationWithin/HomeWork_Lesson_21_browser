package com.homework_21.servlet.city_table.delete;

import com.homework_21.jdbc.JDBC_CRUD_Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete/4")
public class Servlet_Delete_4_DeleteDatabaseByPassword extends HttpServlet {
    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        final String password = request.getParameter("password");
        final String name = request.getParameter("name");
        if (manager.isPasswordValid(password)){
            manager.deleteDatabase(name);
            response.sendRedirect("/HomeWork_Lesson_21_war_exploded/delete/4");
        } else {
            pw.println("<html>");
            pw.println("<body style=\"color:Black; background-color:DarkGray\">");
            for (int i = 0; i < 10; i++) {
                pw.println("<br>");
            }
            pw.println("<table align=\"center\" border=\"35\" >");
            pw.println("<tr>");
            pw.println("<th>");
            pw.println("<h1 align=\"center\" style=\"color:Maroon\"> Access denied.Invalid password. Please try again </h1>");
            pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/delete/4\">");
            pw.println("<button>Try again</button>");
            pw.println("</form>");
            pw.println("</th>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");
        }
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body style=\"color:Black; background-color:DarkGray\">");
        for (int i = 0; i < 7; i++) {
            pw.println("<br>");
        }
        pw.println("<table align=\"center\" border=\"35\" >");
        pw.println("<tr>");
        pw.println("<th>");
        pw.println("<h2 align=\"center\"> <u> Delete form </u></h2>");
        pw.println("<form align=\"center\"  method = \"post\">");
        pw.println("<p><b> password: </b><br>  <input type=\"text\" name = \"password\" size=" + 20 + "required pattern=\"[A-Za-z0-9]*\">");
        pw.println("<p><b> database name: </b><br>  <input type=\"text\" name = \"databaseName\" size=" + 20 + "required pattern=\"[A-Za-z0-9]*\">");
        pw.println("<p><b>                                <input type=\"submit\" value=\"OK\" />");
        pw.println("</form>");

        pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/delete\">");
        pw.println("<button>Cancel</button>");
        pw.println("</form>");
        pw.println("</th>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</body>");
        pw.println("<html>");
        pw.close();
    }
}
