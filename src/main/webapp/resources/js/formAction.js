document.addEventListener("DOMContentLoaded", function () {

    // use document.form["form-name"] to reference the form
    let frm1 = document.forms["frm1"];

// bind the onsubmit property to a function to do some logic
    frm1.onsubmit = function(e) {

        // access the desired input through the var we setup
        let ccSelection = frm1.ccselect.value;
        console.log(ccSelection);

        e.preventDefault();
    }

});