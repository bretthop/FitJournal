<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src="../res/js/lib/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="../res/js/lib/underscore.js"></script>
    <script type="text/javascript" src="../res/js/tmpl.js"></script>
    <script type="text/javascript" src="../res/js/lib/bootstrap.min.js"></script>

    <link media="all" rel="stylesheet" type="text/css" href="../res/css/fitJournal.css" />
    <link media="all" rel="stylesheet" type="text/css" href="../res/css/lib/bootstrap.min.css" />
</head>
    <body>
        <div class="navbar" style="margin-bottom: 0;">
            <div class="navbar-inner">
                <div class="container">
                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a href="#" class="brand">FitJournal</a>
                    <div class="nav-collapse collapse navbar-responsive-collapse">
                        <ul class="nav">
                            <li id="cattleLink" class="${navBean.linkState('energy')}"><a href="/fitJournal/energy">Energy</a></li>
                            <li id="cattleGroupsLink" class="${navBean.linkState('weight')}"><a href="/fitJournal/weight">Weight</a></li>
                        </ul>
                        <form action="" class="navbar-search">
                            <input type="text" placeholder="Search" class="search-query span2">
                            </form>
                        <c:if test="${navBean.currentUser != null}">
                            <div class="pull-right">
                                <a class="btn" href="<c:url value="j_spring_security_logout" />">Logout</a>
                            </div>
                            <div class="pull-right">
                                <span class="navbar-text rightMargin10">${navBean.currentUser.fullName}</span>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${navBean.canSeeSubMenu}">
            <%-- TODO: Fix up this design (the colouring) --%>
            <div class="navbar navbar-inverse">
                <div class="navbar-inner">
                    <div class="container">
                        <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul id="subMenuList" class="nav">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <div id="main" class="container">
