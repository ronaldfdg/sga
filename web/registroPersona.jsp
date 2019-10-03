<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web AG</title>
</head>
<body>
	<h1>Registrar persona</h1>
	<br>
	
	<div style="float:left;">
		<a href="${pageContext.request.contextPath}/ServletControllerUsuario?accion=cerrarSesion">Cerrar sesi&oacute;n</a>
	</div>
	
	<form action="${ pageContext.request.contextPath }/ServletControllerPersona" method="post" name="formulario" onsubmit="return validarFormulario(this);">
		<table>
		
			<input type="hidden" name="accion" value="agregarPersona">
			
			<tr>
				<td>Id: </td>
				<td><input type="number" name="idPersona"></td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td><input type="text" name="nombre"></td>
			</tr>
			<tr>
				<td>Apellido: </td>
				<td><input type="text" name="apellido"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Registrar">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>