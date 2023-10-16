$(document).ready(function() {
    // Selector de estado
    $("#estado-selector").change(function() {
        actualizarListadoIncidentes();
    });

    // Selector de comunidad
    $("#comunidad-selector").change(function() {
        actualizarListadoIncidentes();
    });

    function actualizarListadoIncidentes() {
        const estado = $("#estado-selector").val();
        const comunidadId = $("#comunidad-selector").val();

        // Realizar la solicitud AJAX aquí
        $.get(`/incidentes/${comunidadId}/${estado}`, function(data) {
            // Actualizar la vista con los incidentes obtenidos
            $(".incidente").html(data);
        });
    }
});
