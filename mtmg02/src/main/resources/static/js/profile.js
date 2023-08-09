$().ready(function () {
    $.ajax({
        url: "/account-information",
        success: function(data) {
            $("#profile").html(data);
        }
    });

});