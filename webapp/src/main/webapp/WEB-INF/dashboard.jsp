<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<!-- Bootstrap -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet" media="screen">
<script src="<c:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/webapp"><spring:message code="dashboard.title"/></a><a href="?lang=en">English</a> | <a href="?lang=fr">French</a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle" style="text-transform:capitalize;margin-bottom:30px;font-weight:bold;">
            <i class="fas fa-desktop" style="margin-right: 10px;"></i>
                    ${ maxNbOfRows } <spring:message code="dashboard.cptrFounds"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="/webapp" method="GET" class="form-inline">

                        <input type="search" id="searchName" name="searchName" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="/webapp/add" style="font-weight:bold;"><spring:message code="dashboard.addCptr"/></a> 
                    <a class="btn btn-danger" id="editComputer" href="#" onclick="$.fn.toggleEditMode();" style="font-weight:bold;"><spring:message code="dashboard.deleteCptr"/></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="/webapp/delete" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                    <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fas fa-trash-alt" style="margin-left:6px;"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <a href="/webapp/?sort=name" style="text-transform:uppercase;"><spring:message code="dashboard.cptrName"/></a>
                        </th>
                        <th>
                            <a href="/webapp/?sort=introduced" style="text-transform:uppercase;"><spring:message code="dashboard.cptrIntro"/></a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                        	<a href="/webapp/?sort=discontinued" style="text-transform:uppercase;"><spring:message code="dashboard.cptrDisco"/></a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                        	<a href="/webapp/?sort=company" style="text-transform:uppercase;"><spring:message code="dashboard.cptrCompany"/></a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="c">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${c.getId() }"></td>
							<td><a
								href="/webapp/edit?id=<c:out value="${c.getId()}"></c:out>"
								onclick=""><c:out value="${c.getName()}"></c:out>
							</a></td>
							<td><c:out value="${c.introduced}" /></td>
							<td><c:out value="${c.discontinued}"/></td>
							<td><c:out value="${c.companyId}" /></td>
						</tr>
					</c:forEach>
				</tbody>
            </table>	
        </div>
    </section>
<img src="./img/test.png" alt="" style="width:200px;height:200px;position:fixed;z-index:0%;bottom:6%;left:0;"/>
    <footer class="navbar-fixed-bottom">

        <div class="container text-center">
            <ul class="pagination">
                <li>
                    <a href="/webapp/?page=1&sort=<c:out value="${sort}"></c:out>" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
    	
    				<c:choose>
    					<c:when test="${(maxNbOfRows % nbOfRows) != 0}">
            				<c:set var="totalPage" value="${totalPage + 1}" scope="page"></c:set>
            			</c:when>
    				</c:choose>
						
            		<c:choose>			
				         <c:when test = "${page < 3}">
					          <li><a href="/webapp/?page=1&sort=<c:out value="${sort}"></c:out>">1</a></li>
				              <li><a href="/webapp/?page=2&sort=<c:out value="${sort}"></c:out>">2</a></li>
				              <li><a href="/webapp/?page=3&sort=<c:out value="${sort}"></c:out>">3</a></li>
				              <li><a href="/webapp/?page=4&sort=<c:out value="${sort}"></c:out>">4</a></li>
				              <li><a href="/webapp/?page=5&sort=<c:out value="${sort}"></c:out>">5</a></li>
				         </c:when>
				         
				         <c:when test = "${page > totalPage - 3}">
				         	  <li><a href="/webapp/?page=${totalPage - 4}&sort=<c:out value="${sort}"></c:out>">${totalPage - 4}</a></li>
				              <li><a href="/webapp/?page=${totalPage - 3}&sort=<c:out value="${sort}"></c:out>">${totalPage - 3}</a></li>
				              <li><a href="/webapp/?page=${totalPage - 2}&sort=<c:out value="${sort}"></c:out>">${totalPage - 2}</a></li>
				              <li><a href="/webapp/?page=${totalPage - 1}&sort=<c:out value="${sort}"></c:out>">${totalPage - 1}</a></li>
				              <li><a href="/webapp/?page=${totalPage}&sort=<c:out value="${sort}"></c:out>">${totalPage}</a></li>
				         </c:when>
				         
				         <c:otherwise>
					          <li><a href="/webapp/?page=${page - 2}&sort=<c:out value="${sort}"></c:out>">${page - 2}</a></li>
				              <li><a href="/webapp/?page=${page - 1}&sort=<c:out value="${sort}"></c:out>">${page - 1}</a></li>
				              <li><a href="/webapp/?page=${page}&sort=<c:out value="${sort}"></c:out>">${page}</a></li>
				              <li><a href="/webapp/?page=${page + 1}&sort=<c:out value="${sort}"></c:out>">${page + 1}</a></li>
				              <li><a href="/webapp/?page=${page + 2}&sort=<c:out value="${sort}"></c:out>">${page + 2}</a></li>
				         </c:otherwise>
					</c:choose>

              <li>
				  <a href="/webapp/?page=${totalPage}&sort=<c:out value="${sort}"></c:out>" aria-label="Next">
	                  <span aria-hidden="true">&raquo;</span>
	              </a>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <a href="/webapp/?rows=10"><button type="button" class="btn btn-default">10</button></a>
            <a href="/webapp/?rows=50"><button type="button" class="btn btn-default">50</button></a>
            <a href="/webapp/?rows=100"><button type="button" class="btn btn-default">100</button></a>
        </div>
</div>
    </footer>
<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/dashboard.js"/>"></script>

</body>
</html>