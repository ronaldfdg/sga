<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web GA</title>
</head>
<body>
	<h1>Listado de Personas</h1>
	<br>

	<c:if test="${mensaje!=null}">
		<label>${mensaje}</label>
	</c:if>
	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Apellido</th>
		</tr>
		<c:forEach var="persona" items="${ listPeople }">
		<input type="hidden" name="idPersona" value="${persona.idPersona}">
		<tr>
			<td>${ persona.idPersona }</td>
			<td>${ persona.nombre }</td>
			<td>${ persona.apellidos }</td>
			<td>
				<a href="${ pageContext.request.contextPath }/ServletControllerPersona?accion=obtenerPersona&idPersona=${persona.idPersona}">
					<input type="button" value="Editar">
				</a>
			</td>		
			<td>
				<a href="${ pageContext.request.contextPath }/ServletControllerPersona?accion=eliminarPersona&idPersona=${persona.idPersona}">
					<input type="button" value="Eliminar">
				</a>
			</td>
		</tr>
		</c:forEach>
		
	</table>
	
	<a href="registroPersona.jsp">
		Agregar una nueva persona
	</a>
	<br>
	<a href="${pageContext.request.contextPath}/ServletControllerUsuario?accion=cerrarSesion">Cerrar sesi&oacute;n</a>
</body>
</html>