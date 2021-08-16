<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>New Answer</title>
    </head>
    <body>
        <h1><c:out value="${question.question}"></c:out></h1>
        <h2>Tags:</h2>
        <c:forEach items="${question.tags}" var="tag">
        	<p><c:out value="${tag.subject}"></c:out></p>
        </c:forEach>
        <table>
        	<thead>
        		<tr>
        			<th>Answers</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${question.answers}" var="answer">
	       			<tr>
	       				<td><c:out value="${answer.answer}"></c:out></td>
	       			</tr>
       			</c:forEach>
       		</tbody>
        </table>
        <form action="/questions/${question.id}/answer" method="POST">
        	<div>
        		<label for="answer">Answer</label>
        		<textarea name="answer" id="answer"></textarea>
        	</div>
        	<p><c:out value="${questionError}"></c:out></p>
        	<input type="submit" value="Submit"/>
        </form>
    </body>
</html>