$().ready(function () {
    $.ajax({
        url: "/home",
        success: function (data) {
            $("#homeBody").html(data);
        }
    });
    $('#sidebarCollapse, #dismiss').on('click', function () {
        $('#sidebar').toggleClass('active');
        $('#content').toggleClass('active');
        $('#sidebarCollapse').toggleClass('active');
    });
    $("#employee").click(function () {
        $.ajax({
            url: "/employees",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#movies").click(function () {
        $.ajax({
            url: "/movieList",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#cinema-room").click(function () {
        $.ajax({
            url: "/cinemaRoom",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#booking-list").click(function () {
        $.ajax({
            url: "/bookingList",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#promotion").click(function () {
        $.ajax({
            url: "/promotionList",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#show-date").click(function () {
        $.ajax({
            url: "/show-date",
            success: function (data) {
                $("#homeBody").html(data);
            }
        });
    });
});