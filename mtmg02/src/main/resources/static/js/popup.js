var popupOverlay = document.getElementById("popupOverlay");

function showPopup() {
    popupOverlay.classList.add("show");
}

function hidePopup() {
    popupOverlay.classList.remove("show");
}

window.addEventListener("load", function() {
    showPopup();
});

popupOverlay.addEventListener("click", function(event) {
    if (event.target === popupOverlay) {
        hidePopup();
    }
});