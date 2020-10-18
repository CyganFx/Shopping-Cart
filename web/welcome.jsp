<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Welcome</title>
    <link href='https://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700' rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/accordion.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<style>
    body {
        font-family: "Segoe UI";
        background: antiquewhite;
    }
</style>
<body>

<%-- Preventing redirection without login --%>

<% if (session.getAttribute("email") == null) {
    response.sendRedirect(request.getContextPath() + "/id-check?id=" + application.getInitParameter("correctId"));
} %>

<%-- Using JSTL to get data from sessions --%>

<h1>Welcome ${first_name}!</h1>
<h2>There is a data we got from our database:</h2>
<ul>
    <li>User Id: ${id} </li>
    <li>Full Name: ${first_name} ${last_name}</li>
    <li>Job: ${job} </li>
</ul>

<h2>Choose category of item you are looking for:</h2>
<ul>
    <li><a href="productsController?page=furniture">Furniture</a></li>
    <li><a href="productsController?page=sport">Sport</a></li>
    <li><a href="productsController?page=gadgets">Gadgets</a></li>
</ul>


<div class="accordion">
    <div class="accordion-item">
        <a>Website info:</a>
        <div class="content">
            <p>Servlet version: <%= getServletInfo() %>
            </p>
            <p>Servlet container used: <%= application.getServerInfo() %>
            </p>
        </div>
    </div>
    <div class="accordion-item">
        <a>Contact info:</a>
        <div class="content">
            <p>duman070601@gmail.com</p>
            <p>a.zholamanov@astanait.edu.kz</p>
            <p>a.imangazin@astanait.edu.kz</p>
        </div>
    </div>
</div>
<script src='https://code.jquery.com/jquery-3.2.1.min.js'></script>
<script src="javascript/toggle.js"></script>
</body>
</html>
