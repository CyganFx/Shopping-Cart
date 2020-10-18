<%--
  Created by IntelliJ IDEA.
  User: Думан
  Date: 17.10.2020
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/display.css">
    <title>Furniture</title>
</head>
<body>

<c:set var="productsCounter" value="0"/>
<c:forEach items="${cartlist }" var="products">
    <c:set var="productsCounter" value="${productsCounter + 1 }"/>
</c:forEach>

<a href="productsController?page=showcart">Cart has (<c:out value="${productsCounter}"/>) items</a> <br> <br>

<c:forEach items="${list}" var="product">
    <c:if test="${product.getCategory() == 'furniture' }">
        <div class="table-users">
            <div class="header">Product ID: ${product.getId()}</div>
            <table style="width:100%" cellspacing="0">
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Download</th>
                    <th>Add To Cart</th>
                </tr>
                <tr>
                    <td><img src="images/${product.getImage()}" height="150px" width="150px"></td>
                    <td><c:out value="${product.getName()}"/></td>
                    <td><c:out value="${ product.getPrice()} tenge"/></td>
                    <td><a href="FileDownloadServlet?filename=${product.getImage()}">Download</a></td>
                    <td><a href="productsController?page=addToCart&action=furniture&id=<c:out value="${product.getId()}"/>">Add
                        to
                        Cart</a></td>
                </tr>
            </table>
        </div>
    </c:if>
</c:forEach>
</body>
</html>
