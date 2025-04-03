package com.voting.VotingApp.controller;

import com.voting.VotingApp.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.voting.VotingApp.model.Poll;
import request.Vote;

import java.util.List;

@RestController
@RequestMapping ("/api/polls")
public class PollController {
    private final PollService pollService;

    public PollController (PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll (@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/vote")
    public void vote (@RequestBody Vote vote){
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }
}
