async function loadBoard() {

    const response = await fetch('/api/game');
    const data = await response.json();

    document.getElementById("score").innerText = data.score;

    const boardDiv = document.getElementById("board");
    boardDiv.innerHTML = "";

    data.board.forEach(row => {
        row.forEach(cell => {

            const tile = document.createElement("div");

            tile.className = "tile tile-" + cell;
            tile.innerText = cell === 0 ? "" : cell;

            boardDiv.appendChild(tile);
        });
    });
}

async function newGame() {

    await fetch('/api/game/new', {
        method: 'POST'
    });

    loadBoard();
}

async function move(direction) {

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