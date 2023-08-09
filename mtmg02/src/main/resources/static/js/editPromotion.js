$().ready(function () {
    $("#editPromotion").validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            title: {
                required: true,
                maxlength: 50,
            },
            startTime: {
                required: true,
            },
            endTime: {
                required: true,
            },
            discountLevel: {
                required: true,
            },
            detail: {
                required: true,
                maxlength: 50,
            },
        },
        messages: {
            title: {
                required: "Please enter Title",
                maxlength: "Title is invalid",
            },
            startTime: {
                required: "StartTime is not match with rule",
            },
            endTime: {
                required: "EndTime is not match with rule",
            },
            discountLevel: {
                required: "Please enter Discount Level",
            },
            detail: {
                required: "Please enter Detail",
                maxlength: "Detail is not match with rule",
            },
        }
    });

    $('#editPromotion').submit(function (event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng trang sau khi submit
        if($("#editPromotion").valid()){
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

    $("#back-link").click(function () {
        $.ajax({
            url: "/promotionList",//đổ về trang list promotion
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});