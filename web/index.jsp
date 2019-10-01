<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web GA</title>
</head>
<body>
	<h1>Sistema Gestor de Alumnos</h1>
	
	<br>
	
	<div style="float:left;">
		<a href="${pageContext.request.contextPath}/ServletController?accion=cerrarSesion">Cerrar sesi&oacute;n</a>
	</div>
	
	<a href="${ pageContext.request.contextPath }/ServletController?accion=listarPersonas">
		Listado de Personas
	</a>
	
</body>
</html>