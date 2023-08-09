$(document).ready(function () {
    $("#cinemaRoom").dataTable({
        autoWidth: false,
        ordering: false,
        info: false,
        lengthChange: false,
    });

    $("#add-cinema").click(function () {
        $.ajax({
            url: "/addCinemaRoom",
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $('#cinemaRoom').on('click', '.selection-seat', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/selection-seat/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $('#cinemaRoom').on('click', '.delete-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/deleteCinemaRoom/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});
