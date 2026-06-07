async function loadBoard() {

    async function newGame() {

    await fetch('/api/game/new', {
        method: 'POST'
    });

    loadBoard();
}
    const response = await fetch('/api/game');
    const data = await response.json();

    document.getElementById("score").innerText = data.score;

    const boardDiv = document.getElementById("board");
    boardDiv.innerHTML = "";

    data.board.forEach(row => {
        row.forEach(cell => {

            const tile = document.createElement("div");

            tile.className = "tile";
            tile.innerText = cell === 0 ? "" : cell;

            boardDiv.appendChild(tile);
        });
    });
}

async function move(direction) {

    console.log("Moving:", direction);

    await fetch('/api/game/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            direction: direction
        })
    });

    loadBoard();
}

document.addEventListener("keydown", function(event) {

    console.log("Key Pressed:", event.key);

    if(event.key === "ArrowLeft")
        move("LEFT");

    if(event.key === "ArrowRight")
        move("RIGHT");

    if(event.key === "ArrowUp")
        move("UP");

    if(event.key === "ArrowDown")
        move("DOWN");
});

window.onload = loadBoard;