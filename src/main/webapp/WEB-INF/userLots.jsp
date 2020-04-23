<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.offerALot" var="offer_a_lot"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>

<fmt:message bundle="${text}" key="footer.helpInfo" var="contact_us"/>

<fmt:message bundle="${text}" key="userLots.button.pay" var="pay"/>
<fmt:message bundle="${text}" key="userLots.label.paid" var="paid"/>
<fmt:message bundle="${text}" key="userLots.button.personalData" var="personal_data"/>

<c:url value="/style/userLotsStyle.css" var="stl" />

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="${stl}">
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header class="header">
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=userLots&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class="container">

    <div class="leftcolumn">
        <div class="card">
            <a href="controller?command=profile" class="userProfileLink">${personal_data}</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <div>
                <c:if test="${not empty paymentMessage}">
                    <div class="paymentMessage">${paymentMessage}</div>
                </c:if>
            </div>
            <h4>Lots:</h4>
            <hr>
            <c:forEach items="${lotDtoList}" var="lotDto">
                <div class="col-75">
                    <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">lot №${lotDto.lot.idLot} ${lotDto.picture.name} </a>
                </div>
                <div class="col-25">
                <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">${lotDto.lot.price}$</a>
                    <c:choose>
                        <c:when test="${lotDto.lot.status.getValue() == 'payment-waiting'}">
                            <a href="controller?command=payLot&lotId=${lotDto.lot.idLot}" class="payLink">${pay}</a>
                        </c:when>
                        <c:otherwise>
                            <div class="paidStatus">${paid}</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>

<footer>
        <h4 >${contact_us}:<a href="https://www.google.com/intl/ru/gmail/about/"> auctionAdmin@gmail.com</a></h4>
</footer>

</body>
</html>