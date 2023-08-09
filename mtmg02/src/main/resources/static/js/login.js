$().ready(function () {
    $("#form-login").validate({
        errorClass: "error-message",
        errorElement: "label",

        rules: {
            username: {
                required: true,
                maxlength: 50,
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 50,
            },
        },

        messages: {
            username: {
                required: "Please enter Username",
                maxlength: "Username is invalid",
            },
            password: {
                required: "Please enter Password",
                minlength: "Password is not match with rule",
                maxlength: "Password is not match with rule",
            },
        },
    });
});