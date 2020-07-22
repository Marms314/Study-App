var cards = document.querySelectorAll(".flip-card");

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener('click', function() {cards[i].classList.toggle('is-flipped')});
}

var counter = 0;
var moreCardsButton = document.querySelector("#moreFields");
moreCardsButton.addEventListener('click', moreFields);

function moreFields() {
	counter++;
	var newFields = document.getElementById('fieldToAdd').cloneNode(true);
	newFields.id = '';
	newFields.style.display = '';
	var newField = newFields.childNodes;
	for (var i=0;i<newField.length;i++) {
		var theName = newField[i].name
		if (theName)
			newField[i].name = theName + counter;
	}
	var insertHere = document.getElementById('addFieldHere');
	insertHere.parentNode.insertBefore(newFields,insertHere);
}

window.onload = moreFields;