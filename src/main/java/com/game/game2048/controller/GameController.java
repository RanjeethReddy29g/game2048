package com.game.game2048.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.game2048.model.GameBoard;
import com.game.game2048.model.MoveRequest;
import com.game.game2048.service.GameService;



@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/game")
    public GameBoard getGame() {
        return gameService.getGameBoard();
    }
@PostMapping("/api/game/move")
public GameBoard move(@RequestBody MoveRequest request) {
    return gameService.move(request.getDirection());
}
}