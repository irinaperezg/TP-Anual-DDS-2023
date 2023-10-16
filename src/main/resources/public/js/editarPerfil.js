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
function toggleDropdown() {
    const dropdown = document.querySelector(".dropdown-content");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.dropdown-content a').forEach(function(a) {
        a.addEventListener('click', function(event) {
            event.preventDefault();

            const horario = event.target.getAttribute('data-horario');

            const etiqueta = document.createElement('span');
            etiqueta.classList.add('etiqueta-horario');
            etiqueta.innerHTML = `${horario} <button class="eliminar-horario" onclick="eliminarHorario(this)">x</button>`;

            const container = document.querySelector('.horarios-seleccionados');
            container.appendChild(etiqueta);

            toggleDropdown();
        });
    });

    // Agregar un evento para eliminar los horarios seleccionados cuando se hace clic en la "x"
    document.querySelector('.horarios-seleccionados').addEventListener('click', function(event) {
        if (event.target.classList.contains('eliminar-horario')) {
            event.target.parentElement.remove();
        }
    });
});

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


