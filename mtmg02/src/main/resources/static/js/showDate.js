$(document).ready(function () {
    $("#showDate").dataTable({
        autoWidth: false,
        ordering: false,
        info: false,
        lengthChange: false,
    });

    $("#add-show-date").click(function () {
        $.ajax({
            url: "/add-show-date",
            type: 'GET',
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});