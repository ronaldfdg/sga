/**
 * @author Ronald Dolores
 */

function validarTexto(parametro) {
	var patron = /^[a-zA-Z\s]*$/;
	if (parametro.search(patron)) {
		return false;
	} else {
		return true;
	}
}

function validarFormulario(formulario) {

	var idPersona = formulario.idPersona;

	if (!idPersona || idPersona.value == "") {
		alert("Debe ingresar un id a la persona");
		formulario.idPersona.focus();
		return false;
	}

	var nombre = formulario.nombre;
	if (!nombre || nombre.value == "") {
		alert("Debe ingresar su nombre");
		formulario.nombre.focus();
		return false;
	} else if (validarTexto(nombre.value) == false) {
		alert("No debe ingresar numeros en este campo");
		formulario.nombre.focus();
		formulario.nombre.value = "";
		return false;
	} else if (nombre.value.length < 3) {
		alert("Debe ingresar un nombre mayor de 2 letras");
		formulario.nombre.focus();
		formulario.nombre.value = "";
		return false;
	}

	var apellido = formulario.apellido;
	if (!apellido || apellido.value == "") {
		alert("Debe ingresar su apellido");
		formulario.apellido.focus();
		return false;
	} else if (validarTexto(apellido.value) == false) {
		alert("No debe ingresar numeros en este campo");
		formulario.apellido.focus();
		formulario.apellido.value = "";
		return false;
	} else if (apellido.value.length < 2) {
		alert("No existe apellido de 1 letra");
		formulario.apellido.focus();
		formulario.apellido.value = "";
		return false;
	}

	alert("Registro correcto, enviando datos ...");
	return true;

}
