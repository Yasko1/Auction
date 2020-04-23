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

<fmt:message bundle="${text}" key="offerALot.label.name" var="name"/>
<fmt:message bundle="${text}" key="offerALot.label.name.placeholder" var="placeholder_name"/>
<fmt:message bundle="${text}" key="offerALot.label.firstname" var="firstname"/>
<fmt:message bundle="${text}" key="offerALot.label.firstname.placeholder" var="placeholder_firstname"/>
<fmt:message bundle="${text}" key="offerALot.label.secondname" var="secondname"/>
<fmt:message bundle="${text}" key="offerALot.label.secondname.placeholder" var="placeholder_secondname"/>
<fmt:message bundle="${text}" key="offerALot.label.nationalityOfLot" var="nationality_of_lot"/>
<fmt:message bundle="${text}" key="offerALot.label.nationalityOfLot.choose" var="choose_nationality_of_lot"/>
<fmt:message bundle="${text}" key="offerALot.label.dateOfStart" var="date_of_start"/>
<fmt:message bundle="${text}" key="offerALot.label.year" var="year"/>
<fmt:message bundle="${text}" key="offerALot.label.year.choose" var="choose_year"/>
<fmt:message bundle="${text}" key="offerALot.label.price" var="price"/>
<fmt:message bundle="${text}" key="offerALot.label.price.placeholder" var="placeholder_price"/>
<fmt:message bundle="${text}" key="offerALot.label.photos" var="photos"/>
<fmt:message bundle="${text}" key="offerALot.button.offer" var="offer_button"/>
<fmt:message bundle="${text}" key="offerALot.name.error.pattern.message" var="name_error_pattern_message"/>
<fmt:message bundle="${text}" key="offerALot.price.error.pattern.message" var="price_error_pattern_message"/>

<c:url value="/style/offerALotStyle.css" var="stl"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="${stl}">
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=offerALotPage&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class="card">
    <div class="container">
        <div class="offerALotHeader">${offer_a_lot}</div>

        <form action="controller?command=offerALot" class="offerALotForm" method="post" enctype="multipart/form-data">

            <div class="row">
                <div class="col-25">
                    <label for="name">${name}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="name" name="name" required placeholder="${name}"
                           pattern="^[a-zA-Z0-9-_\.\s]{1,20}$" title="${name_error_pattern_message}">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="year_of_issue">${year}</label>
                </div>
                <div class="col-75">
                    <select id="year_of_issue" name="year_of_issue" required>
                        <option disabled>${choose_year}</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                        <option value="2017">2017</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1980">1980th</option>
                        <option value="1970">1970th</option>
                        <option value="1960">1960th</option>
                        <option value="1950">1950th</option>
                        <option value="1940">1940th</option>
                        <option value="1930">1930th</option>
                        <option value="1920">1920th</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="nationality">${nationality_of_lot}</label>
                </div>
                <div class="col-75">
                    <select id="nationality" name="nationality" required>
                        <option disabled>${choose_engine_volume}</option>
                        <option value="Russia">Russia</option>
                        <option value="Ukrain">Ukrain</option>
                        <option value="Belarus">Belarus</option>
                        <option value="USA">USA</option>
                        <option value="Uk">UK</option>
                        <option value="Swiden">Swiden</option>
                        <option value="Spain">Spain</option>
                        <option value="Check Pepublic">Check Pepublic</option>
                        <option value="Poland">Poland</option>
                        <option value="France">France</option>
                        <option value="Italy">Italy</option>
                        <option value="Canada">Canada</option>
                        <option value="Another..">Another..</option>
                    </select>
                </div>
                
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="firstname">${firstname}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="firstname" name="firstname" required placeholder="${firstname}"
                           pattern="^[a-zA-Z0-9-_\.\s]{1,20}$" title="${name_error_pattern_message}">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="secondname">${secondname}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="secondname" name="secondname" required placeholder="${secondname}"
                           pattern="^[a-zA-Z0-9-_\.\s]{1,20}$" title="${name_error_pattern_message}">
                </div>
            </div> 


            <div class="row">
                <div class="col-25">
                    <label for="price">${price}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="price" name="price" required placeholder="${placeholder_price}"
                           pattern="^\d+(\.\d{2})?$" title="${price_error_pattern_message}">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="date_of_start">${date_of_start}</label>
                </div>
                <div class="col-75">
                    <input type="datetime-local" id="date_of_start" name="date_of_start" required>
                </div>
            </div>

            <div class="row">
                <p>${photos}:</p>
                <input type="file" name="photo" multiple accept="image/*,image/jpeg" class="photoForm" required lang="${sessionScope.language}">
            </div>

            <input type="submit" value="${offer_button}">
        </form>
    </div>
</div>

<footer>
        <h4 >${contact_us}:<a href="https://www.google.com/intl/ru/gmail/about/"> auctionAdmin@gmail.com</a></h4>
</footer>


</body>
</html>