// Helper de Handlebars para verificar si un elemento está en un array
Handlebars.registerHelper('includes', function(arr, value, options) {
    if (arr && arr.indexOf(value) !== -1) {
        return options.fn(this);
    } else {
        return options.inverse(this);
    }
});
