<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShareFile</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap/bootstrap.min.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
<%-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/table.css" /> --%>
	
	
<style>
.padding {
	padding-left: 30px;
	padding-right: 30px;
	padding-top: 5px;
}

.padding2 {
	padding-left: 15px;
	padding-right: 15px;
}
#fancybox-wrap {
	padding: 0px;
}

#fancybox-wrap *,
#fancybox-wrap *:before,
#fancybox-wrap *:after {
    -webkit-box-sizing: content-box !important;
    -moz-box-sizing: content-box !important;
    box-sizing: content-box !important;
}
.icon{
	display:none;
}

</style>



</head>
<body>

<div  class='padding'>
	<table ><tr>
		<td class='padding2'>Hello</td>
		<td class='padding2'>userID:${teamUserBean.users.userID}</td>
		<td class='padding2'>userName:${teamUserBean.users.userName}</td>
		<td class='padding2'>groupID:${teamUserBean.team.teamId}</td>
	</tr></table>
	<br>fileUploadMessage:${message}<br>
</div>
<hr>
<!-- path -->
<div   class='padding'>
<c:set var="path" value="<%= request.getContextPath() %>" />
	<c:forEach var="fileTree" items="${folders}">
	<c:choose>
		<c:when test="${fileTree.fileLevel ==0}">
			<c:set var="path" value="${path}/ShareFile" />
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
<!-- icon -->
<div   class='padding'>
	<a id="insertFile" href="<%= request.getContextPath() %>/shareFile/uploadFile.jsp"><img alt="Upload" src="<%= request.getContextPath() %>/image/fileUploadcloud148.png" /></a>
	<img id="NewFolder" alt ="New Folder" title ="New Folder"  src="<%= request.getContextPath() %>/image/newfolder15.png"  />
	<img id="iconDownload" class="icon" alt ="Download" title ="Download"  src="<%= request.getContextPath() %>/image/fileDownloadCloud134.png" />
	<img id="iconCopy" class="icon" alt ="Copy" title ="Copy"  src="<%= request.getContextPath() %>/image/copyfile19857.png" />
	<img id="iconDelete" class="icon" alt ="Delete" title ="Delete"  src="<%= request.getContextPath() %>/image/delete84453783.png" />
	<img id="iconRename" class="icon" alt ="Rename" title ="Rename"   src="<%= request.getContextPath() %>/image/renameedit42.png" />
	<img id="iconMove" class="icon" alt ="Move" title ="Move"   src="<%= request.getContextPath() %>/image/movesend2.png" />
	
</div>
<br>
<!-- file list -->
<div   class='padding'>
<table class="table">
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
			<c:choose>
				<c:when test="${fileList.fileType == '資料夾'}"><tr id="folder${fileList.fileId}"></c:when>
			    <c:otherwise><tr id="file${fileList.fileId}"></c:otherwise>
			</c:choose>
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
</div>

<!-- folders -->
<div>
<table class="table">
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
</div>



<script >
// 	$(document).ready(function() {
		
// 	});
// 	   <tr id="file922">
	$(function(){
		$("a#insertFile").fancybox();
		$('tr[id^="folder"]').click(function(){
// 			alert('folder');
		});
		$('tr[id^="file"]').click(function(){
// 			alert('file');
		});
	
	});
</script>
</body>
</html>