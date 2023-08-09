$().ready(function () {
    $("#form-addMovie").validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            movieNameEnglish: {
                required: true,
            },
            movieNameVn: {
                required: true,
            },
            fromDate: {
                required: true,
            },
            toDate: {
                required: true,
            },
            actor: {
                required: true,
            },
            movieProductionCompany: {
                required: true,
            },
            director: {
                required: true,
            },
            duration: {
                required: true,
            },
            version: {
                required: true,
            },
            content: {
                required: true,
            },
            image: {
                required: true,
            },
        },

        messages: {
            movieNameEnglish: {
                required: "Please enter Movie Name English",
            },
            movieNameVn: {
                required: "Please enter Movie Name Vn",
            },
            fromDate: {
                required: "Please enter From Date",
            },
            toDate: {
                required: "Please enter To Date",
            },
            actor: {
                required: "Please enter Actor",
            },
            movieProductionCompany: {
                required: "Please enter Movie Production Company",
            },
            director: {
                required: "Please enter Director",
            },
            duration: {
                required: "Please enter Duration",
            },
            version: {
                required: "Please enter Version",
            },
            content: {
                required: "Please enter Content",
            },
            image: {
                required: "Please add Image",
            },
        },
    });

    $("#back-link").click(function () {
        $.ajax({
            url: "/movieList",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $('#form-addMovie').submit(function (event) {
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
        // }
    });
});