package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.io;

public class Presentation extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String html="";
	html+="<!DOCTYPE html>"
+"<html>"
+"<head>"
+	"<title>Twister</title>"
+"<style>"
+"#t:hover {"
+"filter: grayscale(100%);"
+"}"
+"</style>"
+"<link href=\"https://fonts.googleapis.com/css?family=Caveat+Brush\" rel=\"stylesheet\">"
+"</head>"
+"<body style='background-color:#00BFFF'>"
+"<p style='position:relative;text-align:center;top:33vh;margin:0;color:white;font-size:130px;font-family:\"Caveat Brush\", cursive;'>Twister "
+"<span><a id='t' href='http://luluperet.github.io/doc' target='_blank' style='transition: all 2s;' ><img src='https://upload.wikimedia.org/wikipedia/fr/c/c8/Twitter_Bird.svg' width=\"120px\" /></a></span></p>"
+"</body>"
+"</html>";
	
	io.print_html(resp, html);
}
}
