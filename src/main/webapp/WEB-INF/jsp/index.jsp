<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.talbot.customerList.model.Customer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%
List<Customer> customers = (List<Customer>) request.getAttribute( "customers" );
%>
<%
for (Customer customer : customers) {
pageContext.setAttribute("customerInfo", customer.toString() );
%>
<div>${customerInfo}</div>
<%
}
%>
</body>
</html>