<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<html>
<head>
	<title>系统出现了故障 500错误</title>
	<style type="text/css">
		body{
			font-family:Arial, Helvetica, sans-serif;
		}
		.wrap{
			width:1000px;
			margin:0 auto;
		}
		.logo{
			width:650px;
			margin: 0 auto;
		}
		p a{
			color:#eee;
			font-size:13px;
			margin-left:30px;
			padding:5px;
			background:#FF3366;
			text-decoration:none;
			-webkit-border-radius:.3em;
			-moz-border-radius:.3em;
			border-radius:.3em;
		}
		p a:hover{
			color: #fff;
		}
		.footer{
			position:absolute;
			bottom:10px;
			right:10px;
			font-size:12px;
			color:#aaa;
		}
		.footer a{
			color:#666;
			text-decoration:none;
		}

	</style>
</head>


<body>
<div class="wrap">
	<div class="logo">
		<img src="image/500.jpg" alt="error image"  />
	</div>

	<div id="exception">
		<hr width=100%>

		<h2><font color=#DB1260>错误信息如下:</font></h2>
		<p>An exception was thrown: <b> <%=exception.getClass()%>:<%=exception.getMessage()%></b></p>
		<%
			Enumeration<String> e = request.getHeaderNames();
			String key;
			while(e.hasMoreElements()){
				key = e.nextElement();
			}
			e = request.getAttributeNames();
			while(e.hasMoreElements()){
				key = e.nextElement();
			}
			e = request.getParameterNames();
			while(e.hasMoreElements()){
				key = e.nextElement();
			}
		%>
		<%=request.getAttribute("javax.servlet.forward.request_uri") %><br>
		<%=request.getAttribute("javax.servlet.forward.servlet_path") %>
		<p>With the following stack trace:</p>
<pre>
<%
	exception.printStackTrace();
	ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	exception.printStackTrace(new PrintStream(ostr));
	out.print(ostr);
%>
</pre>
		<hr width=100%>
	</div>
</div>

</body>

</html>