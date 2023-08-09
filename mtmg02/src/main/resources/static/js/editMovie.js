$().ready(function () {
    $("#form-edit-movie").validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            movieNameEnglish: {
                required: true,
                maxlength: 50,
            },
            movieNameVn: {
                required: true,
                maxlength: 50,
            },
            fromDate: {
                required: true,
            },
            toDate: {
                required: true,
            },
            actor: {
                required: true,
                maxlength: 50,
            },
            movieProductionCompany: {
                required: true,
                maxlength: 50,
            },
            director: {
                required: true,
                maxlength: 50,
            },
            duration: {
                required: true,
            },
            version: {
                required: true,
            },
            content: {
                required: true,
                maxlength: 250,
            },
        },
        messages: {
            movieNameEnglish: {
                required: "Please enter Movie name [ENG]",
                maxlength: "Movie name [ENG] is invalid",
            },
            movieNameVn: {
                required: "Please enter Movie Name [VN]",
                maxlength: "Movie Name [VN] is not match with rule",
            },
            fromDate: {
                required: "Please re-enter From Date",
            },
            toDate: {
                required: "Please enter To Date",
            },
            actor: {
                required: "Please enter Actor",
                maxlength: "Actor is invalid",
            },
            movieProductionCompany: {
                required: "Please enter Movie Production Company",
                maxlength: "Movie Production Company is invalid",
            },
            director: {
                required: "Please enter Director",
                maxlength: "Director is invalid",
            },
            duration: {
                required: "Please enter Address",
            },
            version: {
                required: "Please enter Version",
                maxlength: "Version is invalid",
            },
            content: {
                required: "Please enter Content",
                maxlength: "Content is invalid",
            },
        }
    });

    $('#form-edit-movie').submit(function (event) {
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

    $("#back-link").click(function () {
        $.ajax({
            url: "/movieList",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});