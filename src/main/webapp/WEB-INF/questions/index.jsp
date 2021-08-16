<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dojo Overflow</title>
    </head>
    <body>
        <h1>Questions Dashboard</h1>
        <table>
        	<thead>
        		<tr>
        			<th>Question</th>
        			<th>Tags</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${questions}" var="question">
        			<tr>
        				<td><a href="/questions/${question.id}"><c:out value="${question.question}"></c:out></a></td>
        				<td>
        					<c:forEach items="${question.tags}" var="tag" varStatus="tagLoop">   					
	        					<span><c:out value="${tag.subject}"></c:out><c:if test="${!tagLoop.last}">, </c:if></span>
							</c:forEach>
        				</td>
        			</tr>
        		</c:forEach>
        	</tbody>
        </table>
        <a href="/questions/new">New Question</a>
    </body>
</html>