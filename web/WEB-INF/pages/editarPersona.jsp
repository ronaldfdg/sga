<%@ page import="com.ronaldfdg.dto.Persona" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web GA</title>
</head>
<body>
	<h1>Editar Persona</h1>

	<form action="${ pageContext.request.contextPath }/ServletController?accion=editarPersona" method="post" name="formulario" onsubmit="return validarFormulario(this);">
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
				<td>
					<input type="submit" value="Editar">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>