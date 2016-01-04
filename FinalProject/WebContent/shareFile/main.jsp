<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/table.css" />
<title>ShareFile</title>
</head>
<body>
Hello<br>
userID:${teamUserBean.users.userID}<br>
userName:${teamUserBean.users.userName}<br>
groupID:${teamUserBean.team.teamId}<br>
error:${errors.pathError}<br>
<hr>
<div>
<c:set var="path" value="<%= request.getContextPath() %>" />
	<c:forEach var="fileTree" items="${folders}">
	<c:choose>
		<c:when test="${fileTree.fileLevel ==0}">
			<a href = "${path}">ShareFile</a>
		</c:when>
		<c:otherwise>
			<c:set var="path" value="${path}/${fileTree.fileName}" />
			 > <a href = "${path}">${fileTree.fileName}</a>
		</c:otherwise>
	</c:choose>
	</c:forEach>
</div>
<br>
<table>
	<thead>
		<tr>
			<td>fileId</td>
			<td>fileName</td>
			<td>fileType</td>
			<td>updateTime</td>
			<td>userId</td>
			<td>userName</td>
			<td>teamId</td>
			<td>teamName</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${! empty fileList}">
		<c:forEach var="fileList" items="${fileList}">
			<tr>    
				<td>${fileList.fileId}</td>
				
				<td>
				<c:choose>
					<c:when test="${fileList.fileType == '資料夾'}"><a href = "${requestURI}/${fileList.fileName}">${fileList.fileName}</a></c:when>
					<c:otherwise>${fileList.fileName}</c:otherwise>
				</c:choose>
				</td>
				
				<td>${fileList.fileType}</td>
				<td>${fileList.updateTime}</td>
				<td>${fileList.userBean.userID}</td>
				<td>${fileList.userBean.userName}</td>
				<td>${fileList.teamBean.teamId}</td>
				<td>${fileList.teamBean.teamName}</td>
				<td>${session.requestURI}</td>
			</tr>
		</c:forEach>
		</c:if>
	</tbody>
</table>

<table>
	<thead>
		<tr>
			<td>FileID</td>
			<td>FileName</td>
			<td>FileLevel</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="fileTree" items="${folders}">
			<tr>
				<td>${fileTree.fileId}</td>
				<td>${fileTree.fileName}</td>
				<td>${fileTree.fileLevel}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



</body>
</html>