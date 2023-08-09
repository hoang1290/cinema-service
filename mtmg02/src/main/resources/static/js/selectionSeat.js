$(document).ready(function () {
    // Sự kiện khi người dùng chọn ghế
    $(document).on('change', 'input[type="checkbox"]', function () {
        var seat = $(this).attr('id'); // Lấy ID của ghế được chọn
        var isSelected = $(this).is(':checked'); // Kiểm tra xem ghế có được chọn hay không

        if (isSelected) {
            // Thêm lớp CSS để thể hiện ghế đã chọn
            $('#' + seat).addClass('selected');
        } else {
            // Xóa lớp CSS của ghế đã bỏ chọn
            $('#' + seat).removeClass('selected');
        }
    });

    $("#back-link").click(function () {
        $.ajax({
            url: "/cinemaRoom",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });


    $('#form-edit-seat').submit(function (event) {
        event.preventDefault(); // Ngăn chặn chuyển hướng trang sau khi submit
        var formData = new FormData(this);
        // var selectedSeats = [];
        // $('.selected').each(function () {
        //   selectedSeats.push($(this).attr('id'));
        // });
        // formData.append("selected-seat",selectedSeats.join(', '))
        $.ajax({
            type: 'post',
            url: $(this).attr('action'), // Lấy URL action từ thuộc tính "action" của form
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    // $('button[type="submit"]').click(function () {
    //   // Lấy danh sách các ghế đã chọn
    //   var selectedSeats = [];
    //   $('.selected').each(function () {
    //     selectedSeats.push($(this).attr('id'));
    //   });
    //
    //   // Hiển thị thông báo với các ghế đã chọn
    //   alert('Các ghế đã chọn: ' + selectedSeats.join(', '));
    // });
});
