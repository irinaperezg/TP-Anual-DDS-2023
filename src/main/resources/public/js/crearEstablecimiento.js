
function guardarEstablecimiento() {
    // Obtener valores del formulario
    const denominacion = document.getElementById('denominacion').value;
    const entidad = document.getElementById('entidad').value;
    const localidad = document.getElementById('localidad').value;
    const prestaciones = Array.from(document.getElementById('prestaciones').selectedOptions).map(option => option.value);

    // Confirmar datos ingresados

    if (denominacion.trim() === "") {
        alert("La denominación no puede estar vacía. Por favor, ingrese una denominación válida.");
        return;
    }
    const confirmacion = confirm(`¿Esta seguro/a de guardar los datos del nueva establecimiento?`);
    // Guardar datos si se confirma
    if (confirmacion) {

        // Aquí puedes realizar la lógica para guardar los datos, por ejemplo, enviar una solicitud al servidor.
        // Puedes utilizar AJAX, Fetch API o cualquier otro método según tus necesidades.
        alert('Establecimiento creado exitosamente');
    }

}
