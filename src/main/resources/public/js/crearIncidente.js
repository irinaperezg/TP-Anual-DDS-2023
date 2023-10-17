function Prestacion(id, servicio, establecimiento, comunidad) {
    this.id = id;
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.comunidad = comunidad;
}

function leerPrestaciones() {
    const opcionesPrestacion = document.querySelectorAll(".prestacion-option");
    const prestacionesLeidas = [];

    for (const prestacionOption of opcionesPrestacion) {
        const id = prestacionOption.value;

        const textContent = prestacionOption.textContent.trim();

        const servicio = textContent.split(" en ")[0];
        const establecimiento = textContent.split(" en ")[1];

        const comunidad = prestacionOption.getAttribute("data-comunidad");

        const prestacion = new Prestacion(id, servicio, establecimiento, comunidad);
        prestacionesLeidas.push(prestacion);
    }

    return prestacionesLeidas;
}

const prestaciones = leerPrestaciones();
const selectComunidad = document.querySelector(".comunidad-selector")
const selectPrestacion = document.querySelector(".prestacion-selector");
const textoComunidad = document.querySelector("#comunidadSeleccionadaTexto");

init();

function init() {
    console.log(prestaciones);

    if (selectComunidad.options.length > 0) {
        selectComunidad.options[0].selected = true;
        textoComunidad.textContent = selectComunidad.options[selectComunidad.selectedIndex].text;

        mostrarPrestaciones();

        if (selectPrestacion.options.length > 0) {
            selectPrestacion.options[0].selected = true;
            console.log(selectPrestacion.options[selectPrestacion.selectedIndex].value);
        }
    }
}

selectComunidad.addEventListener("change", () => {
    textoComunidad.textContent = selectComunidad.options[selectComunidad.selectedIndex].text;

    mostrarPrestaciones();
});

function mostrarPrestaciones() {
    selectPrestacion.innerHTML = "";

    if(selectComunidad.options.length > 0) {
        const comunidadSeleccionada = selectComunidad.options[selectComunidad.selectedIndex].value;
        const prestacionesAMostrar = prestaciones.filter(prestacion => prestacion.comunidad === comunidadSeleccionada);

        for(const prestacionAMostrar of prestacionesAMostrar) {
            const option = document.createElement('option');
            option.classList.add("prestacion-option");
            console.log(prestacionAMostrar.id);
            option.value = prestacionAMostrar.id;
            option.textContent = prestacionAMostrar.servicio + " en " + prestacionAMostrar.establecimiento; // Esto muestra la descripción del servicio
            option.setAttribute("data-comunidad", prestacionAMostrar.comunidad);

            selectPrestacion.appendChild(option);
        }
    }
}

const botonCrear = document.querySelector("#botonCrear");

botonCrear.addEventListener("click", () => {
    crearIncidente();
});

function crearIncidente() {
    const prestacion_id = selectPrestacion.options[selectPrestacion.selectedIndex].value;
    const denominacion = document.querySelector("#denominacion").value;
    const observaciones = document.querySelector("#observaciones").value;

    console.log(prestacion_id);
    console.log(denominacion);
    console.log(observaciones);

    const data = {
        prestacion_id: prestacion_id,
        incidente_denominacion: denominacion,
        incidente_observaciones: observaciones
    };

    console.log(data);

    fetch(`../../incidentes/crear`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json' // Puedes ajustar el encabezado según sea necesario
        },
        body: JSON.stringify(data) // Convierte los datos a JSON
    })
        .catch(error => {
            console.error("Error en la solicitud POST:", error);
        });
}