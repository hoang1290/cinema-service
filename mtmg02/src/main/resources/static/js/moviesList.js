$(document).ready(function () {
    $("#movieList").dataTable({
        ordering: false,
        info: false,
        lengthChange: false,
    });

    $("#addMovie").click(function () {
        $.ajax({
            url: "/addMovie",
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $('#movieList').on('click', '.delete-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/deleteMovie/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    })

    $('#movieList').on('click', '.edit-movie', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/editMovie/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});
