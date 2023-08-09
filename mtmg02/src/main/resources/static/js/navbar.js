$(document).ready(function() {
    $("#search-movie").click(function() {
        $.ajax({
            url: "/search-movie?movieName="+document.getElementById("inputSearch").value,
            success: function(data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#show-time-link").click(function() {
        $.ajax({
            url: "/showtime",
            success: function(data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#logo").click(function() {
        $.ajax({
            url: "/home",
            success: function(data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#profile-link").click(function() {
        $.ajax({
            url: "/profile",
            success: function(data) {
                $("#homeBody").html(data);
            }
        });
    });
    $("#movies").click(function() {
        $.ajax({
            url: "/movieList",
            success: function(data) {
                $("#homeBody").html(data);
            }
        });
    });
});