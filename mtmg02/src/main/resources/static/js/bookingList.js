$(document).ready(function () {
    $("#bookedTickedTable").dataTable({
        autoWidth: false,
        ordering: false,
        language: {
            lengthMenu: "",
            zeroRecords: "No Movie",
            paginate: {
                first: "Preview",
                last: "Last",
                next: "Next",
                previous: "Preview",
            },
            infoFiltered: "",
            search: "Search :&nbsp _INPUT_",
        },
        info: "",
    });
});