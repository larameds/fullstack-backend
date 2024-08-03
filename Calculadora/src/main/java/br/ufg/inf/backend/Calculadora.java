package br.ufg.inf.backend;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/calculadora")
public class Calculadora extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
        
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

        try {
			double num1 = Double.parseDouble(request.getParameter("num1"));
			double num2 = Double.parseDouble(request.getParameter("num2"));
			String operation = request.getParameter("operation");
			
			if (!operation.matches("sum|sub|mult|div")) {
				throw new IllegalArgumentException("Invalid operation");
			}
			
			double result = 0;
			switch(operation) {
				case "sum":
					result = num1 + num2;
					break;
				case "sub":
					result = num1 - num2;
					break;
				case "mult":
					result = num1 * num2;
					break;
				case "div":
					if(num2 == 0) {
						throw new ArithmeticException("Division by zero");
					}
					result = num1 / num2;
					break;
			}
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>Result</title></head>");
			out.println("<body>");
			out.println("<h1>Result: " + result + "</h1>");
			out.println("</body>");
			out.println("</html>");
			
		}
		catch(NumberFormatException e) {
			out.println("Error: invalid parameter");
		}
		catch(IllegalArgumentException | ArithmeticException e) {
			out.println("Error: " + e.getMessage());
		}
    }
    
}
