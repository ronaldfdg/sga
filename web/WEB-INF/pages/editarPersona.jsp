<%@ page import="com.ronaldfdg.dto.Persona" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web GA</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/funciones.js"></script>
</head>
<body>
	<h1>Editar Persona</h1>
	<br>
	
	<div style="float:left;">
		<a href="${pageContext.request.contextPath}/ServletControllerUsuario?accion=cerrarSesion">Cerrar sesi&oacute;n</a>
	</div>
	
	<form action="${ pageContext.request.contextPath }/ServletControllerPersona?accion=editarPersona" method="post" name="formulario"
	 		enctype="multipart/form-data" onsubmit="return validarFormulario(this);">
	 		
		<% Persona persona = (Persona) request.getAttribute("persona"); %>
		
		<table>
			<tr>
				<td><input type="hidden" name="idPersona" value="${persona.idPersona}"></td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td><input type="text" name="nombre" value="${persona.nombre}"></td>
			</tr>
			<tr>
				<td>Apellido: </td>
				<td><input type="text" name="apellido" value="${persona.apellidos}"></td>
			</tr>
			<tr>
				<td>Foto:</td>
				<td><input type="file" name="foto" value="${persona.foto}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Editar">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>