$(document).ready(function() {

    $("#type").change(function() {
        var val = $(this).val();
        if (val == "Карандаш") {
            $("#model").html("<option value='Big'>Big</option>" +
                "<option value='Small'>Small</option>");
            $("#property").html("<option value='Краска белая'>Краска белая</option>" +
                "<option value='Краска красная'>Краска красная</option>" +
                "<option value='Краска черная'>Краска черная</option>");
        } else if (val == "Ручка") {
            $("#model").html("<option value='High'>High</option><option value='Low'>Low</option>" +
                "<option value='Standard'>Standard</option>");
            $("#property").html("<option value='L'>L</option>" +
                "<option value='M'>M</option>");
        }
    });
});