function guardarEstablecimiento() {
    // Obtener valores del formulario
    const denominacion = document.getElementById('denominacion').value;

    const entidadElement = document.getElementById('entidad');
    const entidadId = entidadElement.options[entidadElement.selectedIndex].value;

    const localidadElement = document.getElementById('localidad');
    const localidadId = localidadElement.options[localidadElement.selectedIndex].value;

    const prestacionesElement = document.getElementById('prestaciones');
    const prestacionesSeleccionadas = Array.from(prestacionesElement.selectedOptions).map(option => option.value);

    // Confirmar datos ingresados
    if (denominacion.trim() === "") {
        alert("La denominación no puede estar vacía. Por favor, ingrese una denominación válida.");
        return false; // Evitar que el formulario se envíe si hay un error
    }

    // Confirmar con el usuario
    const confirmacion = confirm(`¿Está seguro/a de guardar los datos del nuevo establecimiento?`);

    // Guardar datos si se confirma
    if (confirmacion) {
        const datos = {
            denominacion: denominacion,
            entidad: entidadId,
            localidad: localidadId,
            prestaciones: prestacionesSeleccionadas
        };

        // Realizar solicitud POST al servidor sin manejar la respuesta
        fetch('/crear-establecimiento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });
        alert("Establecimiento creado correctamente");
        // Evitar que el formulario se envíe automáticamente
        return ;
    }

    // Evitar que el formulario se envíe si no se confirma
    return false;
}
