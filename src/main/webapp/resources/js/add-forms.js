function hideEmptyErrorsRow() {
    var x = document.getElementById("errors");
    if(x.innerHTML == "" || x.innerHTML == null) {
        document.getElementById("errorRow").style.display = " none";
    }
}