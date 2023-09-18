
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

    // Realiza acciones específicas para el registro por correo

  } else if (tipo === 'telefono') {
    botonTelefono.classList.add("activo");
    campoCorreo.style.display = 'none';
    campoTelefono.style.display = 'block';
      campoCorreo.required = false;
      campoTelefono.required = true;

    // Realiza acciones específicas para el registro por teléfono
    
  }
}