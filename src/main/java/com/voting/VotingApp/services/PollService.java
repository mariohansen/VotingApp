package com.voting.VotingApp.services;

import com.voting.VotingApp.model.Poll;
import org.springframework.stereotype.Service;
import com.voting.VotingApp.repositories.PollRepository;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }
}
