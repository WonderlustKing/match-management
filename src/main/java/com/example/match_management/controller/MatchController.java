package com.example.match_management.controller;

import com.example.match_management.dto.MatchResource;
import com.example.match_management.entity.Sport;
import com.example.match_management.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
	this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public MatchResource getMatchById(@PathVariable(value = "id") long id) {
	return matchService.findById(id);
    }

    @PostMapping()
    public void createMatch(@RequestBody MatchResource matchResource) {
	matchService.createMatch(matchResource);
    }

    @PutMapping()
    public void updateMatch(@RequestBody MatchResource matchResource) {
	matchService.updateMatch(matchResource);
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable(value = "id") long id) {
	matchService.deleteMatch(id);
    }

    @GetMapping("/findMatch")
    public List<MatchResource> findMatchBy(@RequestParam(value = "matchDate", required = false) LocalDate matchDate,
	@RequestParam(value = "team", required = false) String team,
	@RequestParam(value = "sport") String sport) {
	return matchService.findMatchesBy(matchDate, team, sport);
    }
}
