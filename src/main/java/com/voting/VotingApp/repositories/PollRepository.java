package com.voting.VotingApp.repositories;

import com.voting.VotingApp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository <Poll,Long> {
}
