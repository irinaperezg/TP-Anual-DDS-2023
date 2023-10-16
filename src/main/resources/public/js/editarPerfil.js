function editarCampo(boton) {
    const campoEditable = boton.closest('.campo');
    const inputText = campoEditable.querySelector('.input-text');
    const saveButton = campoEditable.querySelector('.save-button');

    saveButton.style.display = 'inline-block';
    inputText.contentEditable = true;
    inputText.focus();

    const range = document.createRange();
    range.selectNodeContents(inputText);
    const sel = window.getSelection();
    sel.removeAllRanges();
    sel.addRange(range);

    campoEditable.classList.add('editando');

    document.addEventListener('click', function guardar(e) {
        if (!campoEditable.contains(e.target) && e.target !== saveButton) {
            inputText.contentEditable = false;
            saveButton.style.display = 'none';
            campoEditable.classList.remove('editando');
            document.removeEventListener('click', guardar);
        }
    });

    saveButton.addEventListener('click', function() {
        inputText.contentEditable = false;
        saveButton.style.display = 'none';
        campoEditable.classList.remove('editando');
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const botonesSeleccionadores = document.querySelectorAll(".boton_seleccionador");

    botonesSeleccionadores.forEach(boton => {
        boton.addEventListener("click", function() {

            const grupo = this.closest('.grupo');
            const botonesDelGrupo = grupo.querySelectorAll('.boton_seleccionador');

            botonesDelGrupo.forEach(b => b.classList.add('boton_seleccionado'));
            this.classList.remove('boton_seleccionado');

            const campo = this.getAttribute('data-campo');
            const valor = this.getAttribute('data-valor');

            guardarCampo(campo, valor);
        });

    });
    document.querySelector("#grupo1 .boton_seleccionador:nth-child(1)").click();
    document.querySelector("#grupo2 .boton_seleccionador:nth-child(1)").click();
});

document.addEventListener('DOMContentLoaded', function() {
    const selectLocalidad = document.querySelector('[data-campo="localidad"] .input-select');

    selectLocalidad.addEventListener('change', function() {
        console.log('Localidad seleccionada:', this.value);
    });
});


// Horarios
// Set para almacenar los horarios seleccionados
const selectedHorarios = new Set();

// Función para mostrar/ocultar el menú desplegable
function toggleDropdown() {
    const dropdown = document.querySelector(".dropdown");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

// Función para generar los horarios automáticamente
function generateTimes() {
    const dropdownContent = document.getElementById('dropdown-content');
    for (let i = 0; i < 24; i++) {
        const hour = i.toString().padStart(2, '0') + ':00';
        const link = document.createElement('a');
        link.href = '#';
        link.dataset.horario = hour;
        link.onclick = function () {
            selectHorario(this);
        };
        link.textContent = hour;
        dropdownContent.appendChild(link);
    }
}

// Función para manejar la selección de horarios
function selectHorario(element) {
    const horario = element.getAttribute("data-horario");
    if (!selectedHorarios.has(horario)) {
        selectedHorarios.add(horario);
        element.style.color = "#0CC0DF";
        element.style.pointerEvents = "none";

        const etiqueta = document.createElement('span');
        etiqueta.classList.add('etiqueta-horario');
        etiqueta.innerHTML = `${horario} <button class="eliminar-horario" onclick="eliminarHorario(this, '${horario}')">x</button>`;
        const container = document.querySelector('.horarios-seleccionados');
        container.appendChild(etiqueta);
    } else {
        alert("Este horario ya ha sido seleccionado.");
    }
}

// Función para eliminar horarios seleccionados
function eliminarHorario(buttonElement, horario) {
    selectedHorarios.delete(horario);
    buttonElement.parentElement.remove();
}

// Función para guardar horarios en el servidor
function guardarHorarios() {
    const horariosArray = Array.from(selectedHorarios);
    guardarCampo('horarios', horariosArray.join(','));
}

// Función para enviar datos al servidor
function guardarCampo(campo, valor) {
    $.ajax({
        url: '/perfil',
        type: 'POST',
        data: { campo: campo, valor: valor },
        success: function(response){
            console.log('Campo actualizado exitosamente:', response);
        },
        error: function(error) {
            alert('Error al guardar campo: ' + error.responseText);
        }
    });
}

// Inicialización: generar horarios y agregar eventos
document.addEventListener("DOMContentLoaded", function() {
    generateTimes();  // Genera los horarios automáticamente

    // Evento para eliminar horarios seleccionados cuando se hace clic en la "x"
    document.querySelector('.horarios-seleccionados').addEventListener('click', function(event) {
        if (event.target.classList.contains('eliminar-horario')) {
            event.target.parentElement.remove();
        }
    });
});

function guardarHorarios() {
    const horariosArray = Array.from(selectedHorarios).map(horario => {
        // Asumiendo que todos los horarios son para la fecha actual
        const now = new Date();
        const [hour, minute] = horario.split(':');
        now.setHours(hour, minute, 0, 0);
        return now.toISOString();
    });
    guardarCampo('horarios', JSON.stringify(horariosArray));
}