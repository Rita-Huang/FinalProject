<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="fancy">
		<h4>Choose File to Upload in Server</h4>
		<form action="<%= request.getContextPath() %>/shareFile/fileUpload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" multiple/> 
			<input type="submit" value="upload" />
		</form>
		
	</div>
</body>
</html>