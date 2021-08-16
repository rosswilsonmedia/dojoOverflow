<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>New Question</title>
    </head>
    <body>
        <h1>What is your question?</h1>
        <form action="/questions" method="POST">
        	<div>
	        	<label for="question">Question:</label>
	        	<textarea rows="5" cols="20" name="question" id="question"></textarea>
        	</div>
        	<p><c:out value="${questionError}"></c:out></p>
        	<div>
       			<label for="tags">Tags:</label>
       			<input type="text" name="tags" id="tags"/>
       		</div>
       		<input type="submit" value="Submit"/>
        </form>
    </body>
</html>