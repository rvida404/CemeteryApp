<%--
  Created by IntelliJ IDEA.
  User: Catalin Sorecau
  Date: 1/7/2015
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registru</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <jsp:include page="../fragments/menu.jsp"/>
    <div id="burial-registry-details" style="display: none;">
        <h4 class="text-center">
            <b>Registrul anual de programare a inmormantarilor</b>
        </h4>
        <div style="margin-top: 20px;">
            <div class="form-group col-lg-4">
                <input id="brSearchInput" name="searchCriteria" type="text" class="form-control" required="true"/>
            </div>
            <button type="button" onclick="applyRBFilter();" class="btn btn-default">Cauta</button>
            <button type="button" onclick="refreshRBFilter();" class="btn btn-default">Resetati filtrele</button>
        </div>
        <table id="burial-registry-table" class="table-margins table table-bordered text-center">
            <thead>
            <tr style="background-color: #0080F8;">
                <th class="text-center">Id-ul decedatului</th>
                <th class="text-center">Nume</th>
                <th class="text-center">Prenume</th>
                <th class="text-center">Religie</th>
                <th class="text-center">Ingropat la</th>
                <th class="text-center">Id-ul parcelei</th>
                <th class="text-center">Numele parcelei</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="registry" items="${burialRegistryList}">
                <tr>
                    <td>${registry.deceasedId}</td>
                    <td>${registry.lastName}</td>
                    <td>${registry.firstName}</td>
                    <td>${registry.religion}</td>
                    <fmt:message key="date.pattern" var="pattern"/>
                    <td><fmt:formatDate value="${registry.buriedOn}" pattern="${pattern}"/></td>
                    <td>${registry.parcelId}</td>
                    <td>${registry.parcelName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${pages gt 0}">
            <nav style="text-align: center;">
                <ul class="pagination">
                    <li><a><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                    <c:forEach var="i" begin="1" end="${pages}">
                        <li onclick="getPerPage(${i})" style="cursor:pointer;"><a class="page">${i}</a></li>
                    </c:forEach>
                    <li><a><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                </ul>
            </nav>
        </c:if>
    </div>
</body>
</html>
<input id="brURL" type="hidden" value="${pageContext.request.contextPath}/registers/burialRegistry"/>
<input id="brFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/burialRegistry/filter"/>
<input id="brRefreshFilterURL" type="hidden" value="${pageContext.request.contextPath}/registers/burialRegistry/refreshFilter"/>
<script type="text/javascript">
    $(document).ready(function() {
        $('#container').html($('#burial-registry-details').html());
    });
    function getPerPage(pageNo) {
        var url = $('#brURL').val();
        var data = { pageNo : pageNo };
        CemeteryJs.ajaxCall("GET", url, data, 0, "#burial-registry-table");
    }

    function applyRBFilter() {
        var url = $('#brFilterURL').val();
        var searchCriteria = $('#brSearchInput').val();
        var data = { searchCriteria : searchCriteria };
        CemeteryJs.ajaxCall("GET", url, data, 1, '#burial-registry-details');
    }

    function refreshRBFilter() {
        var url = $("#brRefreshFilterURL").val();
        CemeteryJs.ajaxCall("POST", url, null, 1, '#burial-registry-details');
    }
</script>