<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>upload Ad</title>
	</head>

	<body style="text-align: center;">
		<form action="upload.do" method="post"enctype="multipart/form-data">
			<table width="60%" border="1">
				<tr>
					<td>ad title</td>
					<td>
						
						<input type="text" name="adtitle" value="" >
					</td> 
				</tr>
						<input type="text" name="seller_id" value="" >
					</td> 
				</tr>
				
				<tr>
					<td>
					选择文件:<input type="file" name="file">
						
					</td>
					<td>
						<input type="submit" value="upload">
					</td>
				</tr>
			</table>
		</form>
		<p > ${msg}</p>
	</body>
</html>

