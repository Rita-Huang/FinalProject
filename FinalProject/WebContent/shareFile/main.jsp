<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>ShareFile</title>
</head>
<body>
Hello<br>
userID:${teamUserBean.users.userID}<br>
userName:${teamUserBean.users.userName}<br>
groupID:${teamUserBean.team.teamId}<br>
error:${errors.pathError }<br>

<br>
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

<br><br>
<table>
	<thead>
		<tr>
			<td>fileId</td>
			<td>fileName</td>
			<td>updateTime</td>
			<td>userId</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${! empty fileList}">
		<c:forEach var="fileList" items="${fileList}">
			<tr>    
				<td>${fileList.fileId}</td>
				<td>${fileList.fileName}</td>
				<td>${fileList.updateTime}</td>
				<td>${fileList.userId}</td>
			</tr>
		</c:forEach>
		</c:if>
	</tbody>
</table>

<!-- request.setAttribute("folders", check.getFolders());//List<FileTreeBean> -->
<!-- request.setAttribute("list", list);  //List<ShareFileBean>    -->
<%-- <c:forEach var="fileTreeBean" items="${folders}"> --%>

<%-- </c:forEach> --%>




</body>
</html>