$().ready(function () {
    $('#example').DataTable({
        info:false,
        ordering: false,
        pageLength: 10,
        lengthChange: false,
    });

    $('#example').on('click', '.edit-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/edit-employee/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $('#example').on('click', '.delete-button', function() {
        var id = $(this).data('id');
        $.ajax({
            url: '/delete-employee/' + id,
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });

    $("#btn-add-employee").click(function () {
        $.ajax({
            url: "/add-employee",
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});