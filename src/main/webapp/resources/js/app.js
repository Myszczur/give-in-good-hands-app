document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary

            if (this.currentStep === 5) {

                let bagsQuantity = document.getElementById("bagsQuantity");
                let quantity = document.getElementById("quantity").value;
                let categories = document.querySelectorAll('input[type="checkbox"]:checked');
                let institution = document.querySelector('input[type="radio"]:checked').dataset['name'];
                let institutionName = document.getElementById("institutionName");
                let i = 1;
                let address = document.getElementById("address");
                let street = document.getElementById("street").value;
                let city = document.getElementById("city").value;
                let zipCode = document.getElementById("zipCode").value;
                let shippingDate = document.getElementById("shippingDate");
                let date = document.getElementById("date").value;
                let time = document.getElementById("time").value;
                let pickUpComment = document.getElementById("pickUpComment").value;

                bagsQuantity.textContent = "";
                institutionName.textContent = "";
                address.innerHTML = "";
                shippingDate.innerHTML = "";

                if(quantity <= 4) {
                    bagsQuantity.append(quantity + " worki z kategorii:");
                }else{
                    bagsQuantity.append(quantity + " worków z kategorii:");
                }

                categories.forEach((category) => {
                    if (categories.length > 1 && i < categories.length) {
                        bagsQuantity.append(" " + category.dataset['name'] + ",");
                    } else {
                        bagsQuantity.append(" " + category.dataset['name'] + ".");
                    }
                    i += 1;
                });

                institutionName.innerHTML = ("Dla fundacji " + institution + ".");

                address.appendChild(newLi()).innerHTML = street;
                address.appendChild(newLi()).innerHTML = city;
                address.appendChild(newLi()).innerHTML = zipCode;
                shippingDate.appendChild(newLi()).innerHTML = date;
                shippingDate.appendChild(newLi()).innerHTML = time;
                if (pickUpComment.length === 0) {
                    shippingDate.appendChild(newLi()).innerHTML = "Brak uwag.";
                } else {
                    shippingDate.appendChild(newLi()).innerHTML = pickUpComment;
                }

            }
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }

    function newLi() {
        return document.createElement("li");
    }

});
