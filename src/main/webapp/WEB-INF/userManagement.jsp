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

<fmt:message bundle="${text}" key="userManagement.button.ban" var="ban"/>
<fmt:message bundle="${text}" key="userManagement.button.unban" var="unban"/>
<fmt:message bundle="${text}" key="userManagement.button.lotManagement" var="lot_management"/>
<fmt:message bundle="${text}" key="userManagement.button.personalData" var="personal_data"/>
<fmt:message bundle="${text}" key="userManagement.label.users" var="users"/>

<c:url value="/style/userManagementStyle.css" var="stl"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="${stl}">
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=userManagement&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class="container">

    <div class="leftcolumn">
        <div class="card">
            <a href="controller?command=profile" class="adminProfile link">${personal_data}</a>
            <a href="controller?command=lotManagement" class="lotManagement link">${lot_management}</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>${users}:</h4>
            <hr>
            <c:forEach items="${userList}" var="user">
                <div class="col-75">
                    ${user.firstName}${user.firstName} ${user.lastName} ${user.userName} ${user.email}
                </div>
                <div class="col-25">
                    <c:choose>
                        <c:when test="${user.isBanned() eq false}">
                            <a href="controller?command=banUser&userId=${user.idUser}" class="banLink">${ban}</a>
                        </c:when>
                        <c:when test="${user.isBanned() eq true}">
                            <a href="controller?command=unbanUser&userId=${user.idUser}" class="unbanLink">${unban}</a>
                        </c:when>
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