import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.io;

public class addition extends HttpServlet  {



/**
 * Default constructor.
 */
 public addition() {

 } 
 
 protected void doGet(HttpServletRequest request,
 HttpServletResponse response) throws ServletException, IOException {
	String a= request.getParameter("calcul");
	String[] split = a.split(",");
	int first= Integer.parseInt(split[0]);
	String op = split[1];
	int second= Integer.parseInt(split[2]);
	io.print(second);
	response.setContentType( " text / plain " );
	PrintWriter out = response.getWriter ();
	if (op == "plus") {
			out.println( first+" + "+second+" = "+(first + second));

		
	}else if (op == "moins"){
		out.println( first+" - "+second+" = "+(first-second));

		
	}else if (op == "fois"){
		out.println( first+" * "+second+" = "+(first*second));

		
	}else if (op == "diviser"){
		out.println( first+" / "+second+" = "+(first/second));

		
	}
 }
 
 

}
