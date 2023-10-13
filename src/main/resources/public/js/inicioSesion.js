document.querySelector("#grupo .botonTelefonoYMail:nth-child(1)").click();


function seleccionarTipo(tipo) {
    const botonCorreo = document.getElementById("botonCorreo");
    const botonTelefono = document.getElementById("botonTelefono");
    const campoCorreo = document.getElementById("campoCorreo");
    const campoTelefono = document.getElementById("campoTelefono");

    // Elimina la clase "activo" de ambos botones
    botonCorreo.classList.remove("activo");
    botonTelefono.classList.remove("activo");

    // Aplica la clase "activo" al botón seleccionado
    if (tipo === 'correo') {
        botonCorreo.classList.add("activo");
        campoCorreo.style.display = 'block';
        campoTelefono.style.display = 'none';
        campoCorreo.required = true;
        campoTelefono.required = false;

        botonTelefono.style.backgroundColor = '#BEBEBE';
        botonCorreo.style.backgroundColor = '#0CC0DF';

        // Realiza acciones específicas para el registro por correo

    } else if (tipo === 'telefono') {
        botonTelefono.classList.add("activo");
        campoCorreo.style.display = 'none';
        campoTelefono.style.display = 'block';
        campoCorreo.required = false;
        campoTelefono.required = true;

        botonCorreo.style.backgroundColor = '#BEBEBE';
        botonTelefono.style.backgroundColor = '#0CC0DF';


        // Realiza acciones específicas para el registro por teléfono
    }
}
