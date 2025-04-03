package com.voting.VotingApp.services;

import com.voting.VotingApp.model.OptionVote;
import com.voting.VotingApp.model.Poll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.voting.VotingApp.repositories.PollRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        //Get Poll from DB
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        // Get all options
        List<OptionVote> options = poll.getOptions();

        // If index for vote is not valid throw error
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new RuntimeException("Invalid option index");
        }

        //Get Selected Option
        OptionVote selectedOption = options.get(optionIndex);

        // Increment the vote count
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        // Save incremented option into the database
        pollRepository.save(poll);
    }
}
