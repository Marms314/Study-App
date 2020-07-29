//Allows flashcards to flip
if (document.querySelectorAll(".flip-card")) {
    var cards = document.querySelectorAll(".flip-card");

    for (let i = 0; i < cards.length; i++) {
        cards[i].addEventListener("click", function() {cards[i].classList.toggle("is-flipped")});
    }
}

//Allows the addition of questions and flashcards on create screens
if (document.querySelector("#moreFields")) {
    var counter = 0;
    var moreCardsButton = document.querySelector("#moreFields");
    moreCardsButton.addEventListener("click", moreFields);

    function moreFields() {
        counter++;
        var newFields = document.getElementById("fieldToAdd").cloneNode(true);
        newFields.id = "";
        newFields.style.display = "";
        var newField = newFields.getElementsByClassName("form-control");
        for (var i=0;i<newField.length;i++) {
            var theName = newField[i].name;
            if (theName) {
                newField[i].name = theName.replace(0, counter);
            }
        }
        newFields.innerHTML += '<button type="button" class="btn btn-danger btn-sm col-md-3 my-1" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" aria-label="Left Align">Remove</button>';

        var insertHere = document.getElementById("addFieldHere");
        insertHere.parentNode.insertBefore(newFields,insertHere);
    }

}

//Allows random order of selectable answers in questions
if (document.querySelector(".answersDiv")) {
    var answers = document.querySelectorAll(".answersDiv");

    for (let i = 0; i < answers.length; i++) {
        let random = Math.floor(Math.random()*10);
        console.log(random);
        if (random % 2 === 0) {
            let firstAnswer = answers[i].querySelector(".answer");
            answers[i].appendChild(firstAnswer);
            console.log("was even");
            console.log(firstAnswer);
        }
    }
}