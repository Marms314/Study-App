var cards = document.querySelectorAll(".flip-card");

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener('click', function() {cards[i].classList.toggle('is-flipped')});
}
