<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.PersonVO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Person객체 테이블 표시</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<%
	//PersonVO 객체를 이용하여 list에 여러명을 추가 (5명)
	//list를 request 객체에 담아 jsp로 전송
	ArrayList<PersonVO> list = new ArrayList<>();
	list.add(new PersonVO("홍길동", 45));
	list.add(new PersonVO("홍길순", 35));
	list.add(new PersonVO("홍길자", 17));
	list.add(new PersonVO("홍기동", 5));
	list.add(new PersonVO("홍기철", 2));
	request.setAttribute("list", list);
%>

<!-- 테이블을 생성하여  -->
<table class="table table-hover">
  <thead>
  <tr>
  	<th>번호</th>
  	<th>이름</th>
  	<th>나이</th>
  	<th>연령대</th>
  </tr>
  </thead>
  <tbody>
  	<c:forEach items="${requestScope.list }" var="person" varStatus="i">
  		<tr>
  		<td>${i.count }</td>
  		<td>${person.name }</td>
  		<td>${person.age }</td>
  		<td>
  		<!-- ${person.age >=20 ? "성인" : "미성년" } -->
  		<c:choose>
  			<c:when test="${person.age>=20 }">성인</c:when>
  			<c:when test="${person.age>=15 }">청소년</c:when>
  			<c:when test="${person.age>=5 }">어린이</c:when>
  			<c:otherwise>유아</c:otherwise>
  		</c:choose>
  		
  		</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>
</body>
</html>