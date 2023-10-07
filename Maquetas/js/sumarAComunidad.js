// js/sumarAComunidad.js

document.addEventListener("DOMContentLoaded", function() {
    const selected = document.querySelector(".selected");
    const optionsContainer = document.querySelector(".options-container");
    const optionsList = document.querySelectorAll(".option");
  
    selected.addEventListener("click", () => {
      optionsContainer.classList.toggle("active");
    });
  
    optionsList.forEach((option) => {
      option.addEventListener("click", () => {
        selected.textContent = option.querySelector("label").textContent;
        const description = option.getAttribute("data-description");
        const descriptionElement = document.querySelector(".description");
        if (descriptionElement) {
          descriptionElement.textContent = description;
        }
        optionsContainer.classList.remove("active");
      });
    });
  
    // Agregamos un evento click al documento para cerrar el menú si se hace clic en cualquier otro lugar
    document.addEventListener("click", (e) => {
      if (!selected.contains(e.target) && !optionsContainer.contains(e.target)) {
        optionsContainer.classList.remove("active");
      }
    });
  });
  


  function seleccionarTipo(tipo) {
    const botonObservador = document.getElementById("botonObservador");
    const botonAfectado = document.getElementById("botonAfectado");
  
    // Elimina la clase "activo" de ambos botones
    botonObservador.classList.remove("activo");
    botonAfectado.classList.remove("activo");
  
    if (tipo === 'observador') {
        botonObservador.classList.add("activo");
    
  
      botonAfectado.style.backgroundColor = '#BEBEBE';
      botonObservador.style.backgroundColor = '#0CC0DF';
  
  
    } else if (tipo === 'afectado') {
      botonAfectado.classList.add("activo");
      
      botonObservador.style.backgroundColor = '#BEBEBE';
      botonAfectado.style.backgroundColor = '#0CC0DF';
      
  
    }
  }
  
