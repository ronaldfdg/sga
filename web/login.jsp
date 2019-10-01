<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema Web GA</title>
</head>
<body>
	<h1>Servlet SGA</h1>
	<div style="margin:auto;">
		<form name="formLogin" action="${pageContext.request.contextPath}/ValidarUsuarioServlet" method="post">
			<label>Ingresar cuenta de usuario</label>
			<br>
			<label>Usuario:</label>
			<input type="text" name="username">
			<br>
			<label>Password:</label>
			<input type="password" name="password">
			<br>
			<input type="submit" value="Iniciar sesi&oacute;n">
			<br><br>
			<label>Eres nuevo? </label><a href="registroUsuario.jsp">Registrate aqu&iacute;</a>
		</form>
		<br>
		<c:if test="${mensaje!=null}">
			<label style="color:red; text-decoration: blink;">${mensaje}</label>
		</c:if>
	</div>
</body>
</html>