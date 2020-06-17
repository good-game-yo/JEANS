<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>login</title>
    <link rel="stylesheet" href="static/css/login.css"/>
    <link rel="stylesheet" href="static/css/jeans_header_.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css"/>
</head>
<body>
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="webview">
    <div class="jeans_root">
        <div class="jeans_header">
            <div class="search_left"></div>
            <div class="search_logo">
                <img src="static/images/search.jpg" alt="search" height="30" width="30" />
            </div>
            <div class="search_input"  style="margin-top: 0px">
                <form>
                    <input type="text" class = "search_text"/>
                </form>
            </div>
            <div class="logo_left"></div>
            <div class="logo" >
                <a class="header_a" href="main"><img src="static/images/logo.png" alt="logo" height="30" width="71" /></a>
            </div>

            <c:set var="userid" value="${sessionScope.userid}"/>
            <c:if test="${userid != null}">
                <a class="header_a" href="look_write"><div class="logo_right"> <span class="look_write">Look Write</span></div></a>
            </c:if>

            <div class="my_info">
                <div>
                    <c:set var="userid" value="${sessionScope.userid}"/>
                    <c:if test="${userid != null}">
                        <img src="static/images/mypicture.png" alt="pitcture" height="35" width="40" />
                    </c:if>
                </div>
                <span class="user_id"><c:out value="${sessionScope.usernickname}"></c:out></span>
            </div>

            <div class="logout_left"></div>

            <c:set var="userid" value="${sessionScope.userid}"/>
            <c:choose>
                <c:when test="${userid != null}">
                    <a class="header_a" href="logout"><div class="logout">logout</div></a>
                </c:when>

                <c:otherwise>
                    <a class="header_a" href="loginUser"><div class="login">login</div></a>
                </c:otherwise>
            </c:choose>
            <div class="logout_right"></div>
        </div>
    </div>
    <!-- End of Header-->
    <form  name="loginForm" >
        <div class="container">
            <div class="wrap"></div>
            <div class="main"></div>
            <div class="main_title">Jeans에 로그인!</div>
            <div class="main_idInputbox">
                <input type="text" class="main_idInput" placeholder="ID" name="userid"></input>
            </div>
            <div class="main_pwInputbox">
                <input type="password" class="main_pwInput" placeholder="P/W" name="password"></input>
            </div>
            <div class="main_loginBtnbox">
                <button class="main_loginBtn" type="button" onClick="userLogin()" >LOGIN</button>
            </div>
            <div class="main_joinBtnbox">
                <button class="main_joinBtn" type="button" onclick="y" formaction="joinUser" >JOIN</button>
            </div>
            <div class="main_bar">
                <hr></hr>
            </div>
            <div class="main_noIdea" >
                <a href="https://www.naver.com">계정을 잊어버리셨나요?</a>
            </div>
        </div>
    </form>
</div>
</div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="/static/js/login.js"></script>
</body>
</html>