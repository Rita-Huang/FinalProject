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
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/css/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
	
	
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
.iconNotDisplay{
	display:none;
}
.iconShow{
}

.listBackground{
	background-color: #DFFFDF;
}
.table{
	text-align: center;
}
 
</style>
<script>
window.onload = function () {
}
</script>


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
<div   class='padding' id = navPath>
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
	<a id="insertFile" href="<%= request.getContextPath() %>/shareFile/uploadFile.jsp"><img alt="Upload" title ="Upload" src="<%= request.getContextPath() %>/image/fileUploadcloud148.png" /></a>
	<a id="NewFolder"  href="<%= request.getContextPath() %>/shareFile/newFolder.jsp"><img alt ="New Folder" title ="New Folder"  src="<%= request.getContextPath() %>/image/newfolder15.png"  /></a>
	<img id="iconDownload" class="iconNotDisplay" alt ="Download" title ="Download"  src="<%= request.getContextPath() %>/image/fileDownloadCloud134.png" />
	<img id="iconCopy" class="iconNotDisplay" alt ="Copy" title ="Copy"  src="<%= request.getContextPath() %>/image/copyfile19857.png" />
	<img id="iconDelete" class="iconNotDisplay" alt ="Delete" title ="Delete"  src="<%= request.getContextPath() %>/image/delete84453783.png" />
	<img id="iconRename" class="iconNotDisplay" alt ="Rename" title ="Rename"   src="<%= request.getContextPath() %>/image/renameedit42.png" />
	<img id="iconMove" class="iconNotDisplay" alt ="Move" title ="Move"   src="<%= request.getContextPath() %>/image/movesend2.png" />
	
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
	<tbody id = "fileList">
		<c:if test="${! empty fileList}">
		<c:forEach var="fileList" items="${fileList}">
			<c:choose>
				<c:when test="${fileList.fileType == '資料夾'}">
					<tr id="folder${fileList.fileId}" >
					<td>${fileList.fileId}</td>
					<td><a href ="${requestURI}/${fileList.fileName}"  class='trtda'>${fileList.fileName}</a></td>
					<td>${fileList.fileType}</td>
					<td>-</td>
				</c:when>
			    <c:otherwise>
			    	<tr id="file${fileList.fileId}">
			    	<td>${fileList.fileId}</td>
			    	<td>${fileList.fileName}</td>
			    	<td>${fileList.fileType}</td>
			    	<td>${fileList.updateTime}</td>
			    </c:otherwise>
			</c:choose>
				<td>${fileList.userBean.userID}</td>
				<td>${fileList.userBean.userName}</td>
				<td>${fileList.teamBean.teamId}</td>
				<td>${fileList.teamBean.teamName}</td>
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
<iframe id="downloadFrame" style="display:none"></iframe>


<script >
	$(function(){
		$("a#insertFile").fancybox();
		$('tr[id^="f"]').click(function(){
			if($(this).hasClass('listBackground')){
	 			 $(this).removeClass('listBackground');
	 			icondisplay();
	 		 }else{
//	 			 if($('tr[id^="f"]').hasClass('listBackground')){
//	 				$('tr[id^="f"]').removeClass('listBackground');
//	 			 }//單選
	 			 $(this).addClass('listBackground');
	 			icondisplay();
	 		 }
		});	//end of $('tr[id^="f"]').click(function(){
		
// 		#iconDelete | #iconCopy | #iconMove | #iconRename | #iconDownload
//		class="iconNotDisplay"
		function icondisplay(){
			if($('tr[class="listBackground"]').length==0){
				$('img[id^=icon]').addClass('iconNotDisplay');
			}else if($('tr[class="listBackground"]').length==1){
				$('#iconDelete').removeClass('iconNotDisplay');
				$('#iconCopy').removeClass('iconNotDisplay');
				$('#iconMove').removeClass('iconNotDisplay');
				$('#iconRename').removeClass('iconNotDisplay');
				if($('tr[id^=folder][class="listBackground"]').length>=1){
					$('#iconDownload').addClass('iconNotDisplay');
				}else{
					$('#iconDownload').removeClass('iconNotDisplay');
				}
			}else{
				$('#iconDelete').removeClass('iconNotDisplay');
				$('#iconCopy').removeClass('iconNotDisplay');
				$('#iconMove').removeClass('iconNotDisplay');
				$('#iconRename').addClass('iconNotDisplay');
				$('#iconDownload').addClass('iconNotDisplay');
			}
		}	
	
	
		$('#iconDelete').click(deletefile);
		function deletefile(){
			 var session = {'list': []};
			 $('tr[class^="listBackground"][id^="f"]').each(function(i, selected){ 
				 session.list.push({ 'fileID' : $(selected).attr('id' ) });
			 });//取得選取的id	
			 console.log(session);
			console.log(JSON.stringify(session));
			$.ajax({
				  'type':'post', 
				  'url':'<%= request.getContextPath() %>/ShareFileServlet/deletefile',
				  'dataType':'json',  
				  'data':{list:JSON.stringify(session)},
				  'success':function(data){
					  console.log("here is response");
					  console.log(data)
					  $.each(data,function(i,product){
						  console.log(product.fileID);
						  $('#'+product.fileID).remove();
					  })
				  }
			  });//end of $.ajax({ 
		}//end of function deletefile(){
			
		$("a#NewFolder").fancybox();
		
		$('#iconDownload').click(function(){
			$('tr[class^="listBackground"][id^="f"]').each(function(i, selected){ 
				 console.log( $(selected).attr('id' ) );
				 var iframe = document.getElementById("downloadFrame");
				 iframe .src = "<%= request.getContextPath() %>/ShareFileServlet/downloadfile?fileID="+$(selected).attr('id' );
			 });//取得選取的id
		});//end of $('#iconDownload').click(function(){
		
		$('#iconRename').click(function(){
			var fildID = $('tr[class^="listBackground"]>td:eq(0)').text(); 
			$.ajax({
				  'type':'get', 
				  'url':'<%= request.getContextPath() %>/shareFile/renamefile',
				  'dataType':'json',  
				  'data':{fileId:fildID},
				  'success':function(data){
					  console.log("here is response of #iconRename");
					  console.log(data)
					  $.each(data,function(i,product){
						  console.log(product.fileID);
					  })
				  }
			  });//end of $.ajax({ 
		}); //end of $('#iconRename').click(function(){ 
		
		});//end of $(function(){
</script>
</body>
</html>