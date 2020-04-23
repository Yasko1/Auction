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

<fmt:message bundle="${text}" key="userProfile.label.personalData" var="personal_data"/>
<fmt:message bundle="${text}" key="userProfile.label.firstName" var="first_name"/>
<fmt:message bundle="${text}" key="userProfile.label.lastName" var="last_name"/>
<fmt:message bundle="${text}" key="userProfile.label.email" var="email"/>
<fmt:message bundle="${text}" key="userProfile.label.username" var="username"/>
<fmt:message bundle="${text}" key="userProfile.label.balance" var="balance"/>
<fmt:message bundle="${text}" key="userProfile.label.lots" var="lots"/>
<fmt:message bundle="${text}" key="userProfile.label.balance.topUp" var="top_up_balance"/>
<fmt:message bundle="${text}" key="userProfile.label.balance.topUp.button" var="top_up_button"/>
<fmt:message bundle="${text}" key="userProfile.label.balance.topUp.placeholder" var="top_up_placeholder"/>
<c:url value="/style/userProfileStyle.css" var="stl"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="${stl}">
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=profile&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class = "container">

    <div class="leftcolumn">
        <div class="card">
            <a href="controller?command=userLots" class="lotsLink">${lots}</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>${personal_data}:</h4>
            <jsp:useBean id="user" type="by.epam.lab.entity.User" scope="request"/>
            <div class="col-25">
                <label for="firstName">${first_name}</label>
            </div>
            <div class="col-75">
                <input type="text" id="firstName" name="firstName" value="${user.firstName}" readonly>
            </div>


            <div class="col-25">
                <label for="lastName">${last_name}</label>
            </div>
            <div class="col-75">
                <input type="text" id="lastName" name="lastName" value="${user.lastName}" readonly>
            </div>

            <div class="col-25">
                <label for="username">${username}</label>
            </div>
            <div class="col-75">
                <input type="text" id="username" name="username" value="${user.userName}" readonly>
            </div>

            <div class="col-25">
                <label for="email">${email}</label>
            </div>
            <div class="col-75">
                <input type="text" id="email" name="email"  value="${user.email}" readonly>
            </div>

            <div class="col-25">
                <label for="balance">${balance}</label>
            </div>
            <div class="col-75">
                <input type="text" id="balance" name="balance"  value="${user.balance}$" readonly>
            </div>

            <div class="col-25">
                <label for="topUpBalance">${top_up_balance}:</label>
            </div>
            <div class="col-75">
                <form action="controller" method="post">
                    <input type = "hidden" name = "command" value = "topUpBalance">
                    <div class="col-75">
                        <input type="text" id="topUpBalance" name="topUpBalance" placeholder="${top_up_placeholder}" required>
                    </div>
                    <div class="col-25">
                        <input type="submit" value="${top_up_button}" class="topUpBalanceButton">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<footer>
        <h4 >${contact_us}:<a href="https://www.google.com/intl/ru/gmail/about/"> auctionAdmin@gmail.com</a></h4>  
</footer>

</body>
</html>