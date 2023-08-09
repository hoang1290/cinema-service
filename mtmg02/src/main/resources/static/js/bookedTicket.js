$(document).ready(function () {
    $("#infoTable").dataTable({
        autoWidth: false,
        ordering: false,
        search: {
            addClass: "form-control input-lg col-xs-2 rounded",
        },
        lengthMenu: [
            [10, 20, 30],
            ["10", "20", "30"],
        ],
        language: {
            lengthMenu: "Show &nbsp _MENU_ &nbsp entries",
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
            search: "Search :&nbsp _INPUT_",
        },
    });
});
