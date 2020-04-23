<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="text" var="text" />

<fmt:message bundle="${text}" key="header.button.auction" var="home" />

<fmt:message bundle="${text}" key="footer.helpInfo" var="footer_info" />

<fmt:message bundle="${text}" key="login.label.username" var="username_label" />
<fmt:message bundle="${text}" key="login.label.password"	var="password_label" />
<fmt:message bundle="${text}" key="login.password.placeholder"	var="password_placeholder" />
<fmt:message bundle="${text}" key="login.username.placeholder"	var="username_placeholder" />
<fmt:message bundle="${text}" key="login.button.submit"	var="submit_button" />
<fmt:message bundle="${text}" key="login.username.pattern.error"	var="username_pattern_error" />
<fmt:message bundle="${text}" key="login.username.pattern.error"	var="username_pattern_error" />
<fmt:message bundle="${text}" key="login.error.access.message"	var="error_access" />
<fmt:message bundle="${text}" key="login.error.access.admin.message"	var="error_access_admin" />
<fmt:message bundle="${text}" key="login.error.access.user.message"	var="error_access_user" />
<fmt:message bundle="${text}" key="login.error.login.message"	var="error_login" />
<fmt:message bundle="${text}" key="login.error.account.banned.message"	var="error_account_banned" />

<c:url value="/style/loginStyle.css" var="sty" />
<c:url value="/controller?command=main" var="ma" />
<c:url value="/controller?command=language&currentPage=&language" var="ln" />

<html lang="${sessionScope.language}">
<head>
<meta charset="UTF-8">
<title>AUCTION</title>
<link href="${sty}" rel="stylesheet">

</head>
<body>

	<header>
	<a href="controller?command=main" style="float:left">${home}</a>
			<a href="controller?command=language&currentPage=main&language=${sessionScope.nextLanguage}"
       style="float:right">${sessionScope.nextLanguage}</a>
	</header>


	<div class="container">
		<div>
			<c:if test="${not empty errorAccessMessage}">
				<div class="error">
					<c:choose>
						<c:when
							test="${errorAccessMessage == 'You should login to view this page'}">
                            ${error_access}
                        </c:when>
						<c:when
							test="${errorAccessMessage == 'You should login as admin to view this page'}">
                            ${error_access_admin}
                        </c:when>
						<c:when
							test="${errorAccessMessage == 'You should login as user to view this page'}">
                            ${error_access_user}
                        </c:when>
					</c:choose>
				</div>
			</c:if>
		</div>
		<div class="wrap-login100">
			<form action="controller" class="loginForm" method="post">

				<span class="login100-form-title p-b-26"> WELCOME </span> 
				 
				 <input	type="hidden" name="command" value="login">

				<div class="wrap-input100 validate-input">
					<div class="col-25">
						<label for="username">${username_label}</label>
					</div>
					<input class="input100" type="text" id="username" name="username"
						placeholder="${username_placeholder}" required
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
						title="${username_pattern_error}">
				</div>

				<div class="wrap-input100 validate-input">
					<div class="col-25">
						<label for="password">${password_label}</label>
					</div>
					<input class="input100" type="password" id="password"
						name="password" placeholder="${password_placeholder}" required>
				</div>

				<div class="container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
						<button class="login100-form-btn" type="submit"
							value="${submit_button}">LOGIN</button>
					</div>
				</div>


				<div class="text-center p-t-115">
					<span class="txt1"> Don’t have an account? </span> <a class="txt2"
						href="https://www.google.com/intl/ru/gmail/about/"> Contact to us </a>
				</div>

			</form>
		</div>
	</div>


</body>
</html>