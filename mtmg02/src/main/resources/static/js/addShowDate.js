$(document).ready(function () {
    $("#form-add-show-date").validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            movieId: {
                required: true,
            },
            localDate: {
                required: true,
            },
        },

        messages: {
            movieId: {
                required: "Please choose Movie",
            },
            localDate: {
                required: "Please choose Date",
            },
        },
    });

    $('#form-add-show-date').submit(function (event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng trang sau khi submit
        // if($("#form-editAccount").valid()){
        var formData = new FormData(this);

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'), // Lấy URL action từ thuộc tính "action" của form
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $("#back-link").click(function () {
        $.ajax({
            url: "/show-date",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});