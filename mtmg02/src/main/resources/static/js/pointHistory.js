$(document).ready(function () {
    $("#infoTable").dataTable({
        autoWidth: false,
        ordering: false,
        search: {
            addClass: "form-control input-lg col-xs-2 rounded",
        },
        lengthMenu: false,
        language: {
            lengthMenu: "Show &nbsp _MENU_ &nbsp enties",
            zeroRecords: "No Movie",
            info: "",
            infoEmpty: "",
            paginate: {
                first: "Preview",
                last: "Last",
                next: "Next",
                previous: "Preview",
            },
            infoFiltered: "",
            search: "_INPUT_",
            searchPlaceholder: "Search",
        },
    });
});
