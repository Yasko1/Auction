<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="fullFilePath" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.offerALot" var="offer_a_lot"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>

<fmt:message bundle="${text}" key="footer.helpInfo" var="contact_us"/>

<fmt:message bundle="${text}" key="main.table.header.photo" var="photo"/>
<fmt:message bundle="${text}" key="main.table.header.currentPrice" var="current_price"/>
<fmt:message bundle="${text}" key="main.table.header.name" var="name"/>
<fmt:message bundle="${text}" key="main.table.header.painter" var="painter"/>
<fmt:message bundle="${text}" key="main.table.header.yearOfPainting" var="year_of_painting"/>
<fmt:message bundle="${text}" key="main.table.lotInfo" var="lot_info"/>
<fmt:message bundle="${text}" key="main.table.bidButton" var="bid_button"/>

<fmt:message bundle="${text}" key="main.finder.header.Finder" var="finder"/>
<fmt:message bundle="${text}" key="main.finder.parameter.nationality" var="nationality"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year" var="year"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year.option.All" var="all"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year.to" var="to"/>
<fmt:message bundle="${text}" key="main.finder.parameter.damaged.option.no" var="no"/>
<fmt:message bundle="${text}" key="main.finder.button.find" var="find"/>

<c:url value="/style/mainStyl.css" var="stl"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="${stl}">
    <meta charset="UTF-8">
    <title>AUCTION</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=main&language=${sessionScope.nextLanguage}"
       style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>

<div class="card">
    <div class="row">

        <div class="middle">
            <div class="finderHeader">${finder}</div>
            <form action="" class="finder" method="get">
                <input type="hidden" name="command" value="findLots">
                
                
                <div class="row">
                    <div class="col-30">
                        <label for="nationality_type">${nationality}</label>
                    </div>
                    <div class="col-70">
                        <select id="nationality_type" name="nationality">
                            <option value="All">${all}</option>
                            
                        </select>
                    </div>
                </div>                

                <div class="row">
                    <div class="col-30">
                        <label for="year_of_painting_from">${year}</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_painting_from" name="year_of_painting_from">
                            <option value="All">${all}</option>
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
                            <option value="1980">1980</option>
                            <option value="1970">1970</option>
                            <option value="1960">1960</option>
                            <option value="1950">1950</option>
                            <option value="1940">1940</option>
                            <option value="1930">1930</option>
                            <option value="1920">1920</option>
                        </select>
                    </div>
                    <div class="col-10">
                        <label for="year_of_painting_to">${to}</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_painting_to" name="year_of_painting_to">
                            <option value="All">${all}</option>
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
                            <option value="1980">1980</option>
                            <option value="1970">1970</option>
                            <option value="1960">1960</option>
                            <option value="1950">1950</option>
                            <option value="1940">1940</option>
                            <option value="1930">1930</option>
                            <option value="1920">1920</option>
                        </select>
                    </div>
                </div>

                <div class="findButtonDiv">
                    <input type="submit" value="${find}" id="findButton">
                </div>
            </form>
        </div>

        <div class="side">
            <table id="lots" class="lots">
                <tr>
                    <th valign="top">${photo}</th>
                    <th width="50px" valign="top">${year_of_painting}</th>
                    <th width="150px" valign="top">${painter}</th>
                    <th width="200px" valign="top">${name}</th>
                    <th width="60px" valign="top">${current_price}$</th>
                </tr>
                <c:forEach items="${lotDtoList}" var="lotDto">
                    <tr>
                         <td>
                            <div>
                                <c:if test="${lotDto.photos.size() != 0}">
                                    <c:set var="photoName" value="${lotDto.photos.get(0).getUrl()}" scope="page"/>
                                    <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">
                                    <img src="${ctg:filePath(photoName)}" alt="lot photo" width="250px"></a>
                                </c:if>
                            </div>
                        </td>
                        <td width="50px" valign="top"><c:out value="${lotDto.picture.getYear()}"/></td>
                        <td width="150px" valign="top"><c:out value="${lotDto.picture.idPainter}"/></td>
                        <td width="200px" valign="top"><div><c:out value="${lotDto.picture.name}"/></div></td>
                        <td width="60px" valign="top">
                            <div><c:out value="${ lotDto.lot.price }$"/></div>
                            <div>
                                <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}" class="bidButton">${bid_button}</a>
                            </div>
                        </td>
                        
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</div>

<footer>
        <h4 >${contact_us}:<a href="https://www.google.com/intl/ru/gmail/about/"> auctionAdmin@gmail.com</a></h4>
</footer>

</body>
</html>