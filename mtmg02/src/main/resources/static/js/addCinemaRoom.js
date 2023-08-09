$().ready(function () {
    $('#addCinemaRoom').validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            cinemaName: {
                required: true,
                maxlength: 50,
            },
            seatQuantity: {
                required: true,
                minlength: 1,
                maxlength: 2,
            },
            screen: {
                required: true,
                maxlength: 50,
            },

        },
        messages: {
            cinemaName: {
                required: "Please enter Cinema Name",
                maxlength: "Cinema Name is invalid",
            },
            seatQuantity: {
                required: "Please enter Seat Quantity",
                minlength: "Seat Quantity is not match with rule",
                maxlength: "Seat Quantity is not match with rule",
            },
            screen: {
                required: "Please re-enter Screen",
                maxlength: "Screen is not match with rule",
            },
        }
    });

    $('#addCinemaRoom').submit(function (event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng trang sau khi submit
        if ($("#addCinemaRoom").valid()) {
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
        }
    });

    $("#back-addCinemaRoom").click(function () {
        $.ajax({
            url: "/cinemaRoom",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});
