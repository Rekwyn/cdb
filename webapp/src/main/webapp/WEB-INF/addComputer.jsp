<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Bootstrap -->

<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet" media="screen">
<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script src="<c:url value="/js/frontValidation.js"/>"></script>

</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a style="line-height:35px;margin-right:20px;" class="navbar-brand" href="/webapp"><spring:message code="dashboard.title"/></a><a style="line-height:70px;" href="?lang=en">English</a> | <a href="?lang=fr">French</a>
        	<a style="float:right;line-height:70px;" href="/webapp/logout"><spring:message code="dashboard.logout"/></a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="dashboard.addCptr"/></h1>
                    <form id="addComputerForm" action="/webapp/add" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="dashboard.cptrName"/></label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="dashboard.cptrIntro"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="dashboard.cptrDisco"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="dashboard.cptrCompany"/></label>
                                <select class="form-control" name="companyId" id="companyId" >
			                    	<c:forEach items="${companies}" var="c" >
				                        <option value="<c:out value='${c.getId()}'/>"><c:out value='${c.getName()}'/></option>
				                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="/webapp" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>

</html>

