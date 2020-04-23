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

<fmt:message bundle="${text}" key="lotManagement.button.personalData" var="personal_data"/>
<fmt:message bundle="${text}" key="lotManagement.button.userManagement" var="user_management"/>
<fmt:message bundle="${text}" key="lotManagement.button.confirm" var="confirm"/>
<fmt:message bundle="${text}" key="lotManagement.button.refuse" var="refuse"/>
<fmt:message bundle="${text}" key="lotManagement.label.lots" var="lots"/>

<c:url value="/style/lotManagmStyle.css" var="stllotma"/> 
 
<html lang="${sessionScope.language}">
<head>
    <link href="${stllotma}" rel="stylesheet" type="text/css" >
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=lotManagement&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class="container">

    <div class="leftcolumn">
        <div class="card">
            <a href="controller?command=profile" class="adminProfile link">${personal_data}</a>
            <a href="controller?command=userManagement" class="userManagement link">${user_management}</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>${lots}:</h4>
            <hr>
            <c:forEach items="${lotDtoList}" var="lotDto">
                <div class="col-75">
                   <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}" >№${lotDto.lot.idLot}</a>
                   <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}"> " ${lotDto.picture.name} " </a>
                   <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">${lotDto.lot.price}$</a>
                </div>
                <div class="col-25">
                    <div class="col-50">
                        <a href="controller?command=confirmLot&lotId=${lotDto.lot.idLot}" class="confirmLink">${confirm}</a>
                    </div>
                    <div class="col-50">
                        <a href="controller?command=refuseLot&lotId=${lotDto.lot.idLot}" class="refuseLink">${refuse}</a>
                    </div>
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
