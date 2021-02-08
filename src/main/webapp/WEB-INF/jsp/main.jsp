<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>小春论坛</title>
</head>
<body>
<h1>${user.userName},欢迎您进入小春论坛，您当前积分为${user.credits};</h1>
<h1>老板最爱的车，名称${boss}名称${boss.niceCar.name}品牌${boss.niceCar.brand}速度${boss.niceCar.maxSpeed}</h1>
<h2>车库</h2>
<c:forEach items="${cars}" var="car">
    汽车<c:out value="${car.name}"></c:out>, 编号<c:out value="${car}"></c:out><br>
</c:forEach>
</body>
</html>