document.addEventListener("DOMContentLoaded", function() {
    const cerrarSesionLink = document.getElementById("cerrarSesionLink");

    cerrarSesionLink.addEventListener("click", function(event) {
        event.preventDefault(); // Evita que el enlace se siga al hacer clic

        // Muestra un cuadro de diálogo de confirmación
        const confirmacion = confirm("¿Seguro/a que deseas cerrar la sesión?");

        if (confirmacion) {
            // Si el usuario confirma, redirige a la página de cierre de sesión
            window.location.href = "/logout"; // Reemplaza con la URL correcta
        }
    });
});

