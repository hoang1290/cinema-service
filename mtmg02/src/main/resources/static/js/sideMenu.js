$(document).ready(function() {
    $("#account-information").click(function() {
        $.ajax({
            url: "/account-information", // Đường dẫn đến máy chủ để lấy thông tin accountInformation
            success: function(data) {
                $("#profile").html(data); // Include thông tin vào phần tử có id "profile"
            }
        });
    });

    $("#history").click(function() {
        $.ajax({
            url: "/point-history", // Đường dẫn đến máy chủ để lấy thông tin pointHistory
            success: function(data) {
                $("#profile").html(data); // Include thông tin vào phần tử có id "profile"
            }
        });
    });
    $("#booked-Ticket").click(function() {
        $.ajax({
            url: "/booking-ticket", // Đường dẫn đến máy chủ để lấy thông tin pointHistory
            success: function(data) {
                $("#profile").html(data); // Include thông tin vào phần tử có id "profile"
            }
        });
    });
});