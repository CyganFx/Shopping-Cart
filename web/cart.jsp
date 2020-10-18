<%--
  Created by IntelliJ IDEA.
  User: Думан
  Date: 18.10.2020
  Time: 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/display.css">
    <title>Title</title>
</head>
<body>
<c:set var="productsCounter" value="0"/>
<c:forEach items="${cartlist }" var="products">
    <c:set var="productsCounter" value="${productsCounter + 1 }"/>
</c:forEach>

<c:choose>
    <c:when test="${productsCounter == 0}">
        <h4>Your shopping bag is empty!</h4>
    </c:when>
    <c:otherwise>
        <h4>My shopping bag (<c:out value="${productsCounter}"/>) </h4>
    </c:otherwise>
</c:choose>

<c:set var="totalPrice" value="0"/>
<c:forEach items="${cartlist}" var="products">
    <c:forEach items="${list}" var="product">
        <c:if test="${products == product.getId() }">
            <div class="table-users">
                <div class="header">Product ID: ${product.getId()}</div>
                <table style="width:100%" cellspacing="0">
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Remove</th>
                    </tr>
                    <tr>
                        <td><img src="images/${product.getImage()}" height="150px" width="150px"></td>
                        <td><c:out value="${product.getName()}"/></td>
                        <td><c:out value="${ product.getPrice()} tenge"/></td>
                        <td><a href="productsController?page=remove&id=<c:out value="${product.getId()}"/>">Remove</a>
                        </td>
                    </tr>
                </table>
            </div>
            <center><c:set var="totalPrice" value="${totalPrice + product.getPrice() }"/></center>
        </c:if>
    </c:forEach>
</c:forEach>

<h3>Total price: <c:out value="${totalPrice} tenge"/></h3>
</body>
</html>
