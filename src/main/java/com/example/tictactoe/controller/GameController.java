package com.example.tictactoe.controller;

import com.example.tictactoe.model.GameState;
import com.example.tictactoe.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private static final String SESSION_KEY = "gameState";

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String showGame(HttpSession session, Model model) {
        GameState state = getOrCreateState(session);
        model.addAttribute("game", state);
        // Integer[] so Thymeleaf th:each iterates correctly (int[] won't work)
        model.addAttribute("indices", new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        return "game";
    }

    @PostMapping("/game/move")
    public String makeMove(@RequestParam int index, HttpSession session) {
        GameState state = getOrCreateState(session);
        gameService.makeMove(index, state);
        // Must setAttribute again so the session serialises the updated state
        session.setAttribute(SESSION_KEY, state);
        return "redirect:/game";
    }

    @PostMapping("/game/reset")
    public String resetGame(HttpSession session) {
        // Create a brand-new state — don't mutate the old one
        GameState state = new GameState();
        session.setAttribute(SESSION_KEY, state);
        return "redirect:/game";
    }

    private GameState getOrCreateState(HttpSession session) {
        GameState state = (GameState) session.getAttribute(SESSION_KEY);
        if (state == null) {
            state = new GameState();
            session.setAttribute(SESSION_KEY, state);
        }
        return state;
    }
}
