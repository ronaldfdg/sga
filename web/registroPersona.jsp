<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema web AG</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/funciones.js"></script>
</head>
<body>
	<h1>Registrar persona</h1>
	<br>
	
	<div style="float:left;">
		<a href="${pageContext.request.contextPath}/ServletControllerUsuario?accion=cerrarSesion">Cerrar sesi&oacute;n</a>
	</div>
	
	<form action="${ pageContext.request.contextPath }/ServletControllerPersona?accion=agregarPersona" method="post" name="formulario" 
			enctype="multipart/form-data" onsubmit="return validarFormulario(this);">
		<table>
			
			<tr>
				<td>Id: </td>
				<td><input type="number" name="idPersona" id="idPersona"></td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td><input type="text" name="nombre" id="nombre"></td>
			</tr>
			<tr>
				<td>Apellido: </td>
				<td><input type="text" name="apellido" id="apellido"></td>
			</tr>
			<tr>
				<td>Foto:</td>
				<td><input type="file" name="foto"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="Registrar">
					<input type="reset" value="Limpiar">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>