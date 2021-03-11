package com.homework_21.servlet.city_table.read;

import com.homework_21.jdbc.JDBC_CRUD_Manager;
import com.homework_21.json.JSON_City_table_Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/read/2")
public class Servlet_Read_2_CitiesByPopulationRange extends HttpServlet {

    JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();
    JSON_City_table_Parser parser = new JSON_City_table_Parser();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        final String lowPopulation = request.getParameter("lowerPopulation");
        final String uppPopulation = request.getParameter("upperPopulation");
        final int lowerPopulation = Integer.parseInt(lowPopulation);
        final int upperPopulation = Integer.parseInt(uppPopulation);
        if (lowerPopulation <= upperPopulation){
            ArrayList<String> cityList = manager.readByPopulationRange(lowerPopulation, upperPopulation);
            if (cityList.isEmpty()){
                pw.println("<html>");
                pw.println("<body style=\"color:Black; background-color:DarkGray\">");
                for (int i = 0; i < 7; i++) {
                    pw.println("<br>");
                }
                pw.println("<table align=\"center\" border=\"35\" >");
                pw.println("<tr>");
                pw.println("<th>");
                pw.println("<h1 align=\"center\" style=\"color:Maroon\"> <u> No such cities are stored in the database </u></h1>");
                pw.println("<h2 align=\"center\" style=\"color:Maroon\"> Range entered incorrectly. Please try again </h2>");
                pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/read/2\">");
                pw.println("<button>Try again</button>");
                pw.println("</form>");
                pw.println("</th>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("</body>");
                pw.println("</html>");
            } else{
                pw.println("<html>");
                pw.println("<body style=\"color:Black; background-color:DarkGray\">");
                pw.println("<table align=\"center\" border=\"1\" >");
                pw.println("<th> ID </th>");
                pw.println("<th> Name </th>");
                pw.println("<th> District </th>");
                pw.println("<th> Country code </th>");
                pw.println("<th> Population </th>");
                pw.println("</tr>");
                for (String s: cityList) {
                    pw.println("<tr>" +
                            "<td align=\"center\">" + parser.parseID(s)          + "</td>" +
                            "<td align=\"center\">" + parser.parseName(s)        + "</td>" +
                            "<td align=\"center\">" + parser.parseDistrict(s)    + "</td>" +
                            "<td align=\"center\">" + parser.parseCountryCode(s) + "</td>" +
                            "<td align=\"center\">" + parser.parsePopulation(s)  + "</td>" +
                            "</tr>");
                }
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/read\">");
                pw.println("<button>Cancel</button>");
                pw.println("</form>");
                pw.println("</body>");
                pw.println("</html>");
            }
        } else {
            pw.println("<html>");
            pw.println("<body style=\"color:Black; background-color:DarkGray\">");
            for (int i = 0; i < 7; i++) {
                pw.println("<br>");
            }
            pw.println("<table align=\"center\" border=\"35\" >");
            pw.println("<tr>");
            pw.println("<th>");
            pw.println("<h1 align=\"center\" style=\"color:Maroon\"> Range entered incorrectly. Please try again </h1>");
            pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/read/2\">");
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
        pw.println("<h2 align=\"center\"> <u> Read form </u></h2>");
        pw.println("<form align=\"center\"  method = \"post\">");
        pw.println("<p><b> lower range population: </b><br>  <input type=\"text\" name = \"lowerPopulation\" size=" + 20 + "required pattern=\"[0-9]*\">");
        pw.println("<p><b> upper range population: </b><br>  <input type=\"text\" name = \"upperPopulation\" size=" + 20 + "required pattern=\"[0-9]*\">");
        pw.println("<p><b>                                <input type=\"submit\" value=\"Show\" />");
        pw.println("</form>");

        pw.println("<form align=\"center\" action=\"/HomeWork_Lesson_21_war_exploded/read\">");
        pw.println("<button>Cancel</button>");
        pw.println("</form>");
        pw.println("</th>");
        pw.println("</tr>");
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
}
