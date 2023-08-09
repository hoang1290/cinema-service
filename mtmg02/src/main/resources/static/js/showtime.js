$().ready(function () {
    $(".link").click(function () {
        var id = $(this).data('id');
        $.ajax({
            url: '/showtime?date=' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $('.form-select-movie').submit(function (event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng trang sau khi submit
        var formData = new FormData(this);

        $.ajax({
            type: 'get',
            url: $(this).attr('action'), // Lấy URL action từ thuộc tính "action" của form
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#homeBody").html(data);
            }
        });

    });
})