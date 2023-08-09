$().ready(function () {
    $(".promotion").DataTable({
        autoWidth: false,
        ordering: false,
        info: false,
        lengthChange: false,
    });

    $("#addPromotion").click(function () {
        $.ajax({
            url: "/addPromotion",
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $('.promotion').on('click', '.edit-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/editPromotion/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $('.promotion').on('click', '.delete-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/deletePromotion/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});