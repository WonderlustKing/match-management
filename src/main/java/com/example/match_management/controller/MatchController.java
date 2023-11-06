package com.example.match_management.controller;

import com.example.match_management.dto.MatchOddResource;
import com.example.match_management.dto.MatchResource;
import com.example.match_management.service.MatchOddService;
import com.example.match_management.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Match-management", description = "Match management system API")
@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;
    private final MatchOddService matchOddService;

    @Autowired
    public MatchController(MatchService matchService, MatchOddService matchOddService) {
	this.matchService = matchService;
	this.matchOddService = matchOddService;
    }

    @Operation(
		    summary = "Fetch match by the given ID",
		    description = "fetches a specific match entity")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
		    		@ApiResponse(responseCode = "404", description = "Cannot find match with the given ID", content = { @Content(schema = @Schema()) })})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MatchResource> getMatchById(@PathVariable long id) {
	return new ResponseEntity<>(matchService.findMatchById(id), HttpStatus.OK);
    }

    @Operation(
		    summary = "Adds a match",
		    description = "Adds a new match entity")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "successfully added a match")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<MatchResource> createMatch(@io.swagger.v3.oas.annotations.parameters.RequestBody(
		    					content = @Content(schema=@Schema(implementation = MatchResource.class)))
    		@RequestBody MatchResource matchResource) {
	return new ResponseEntity<>(matchService.createMatch(matchResource), HttpStatus.CREATED);
    }

    @Operation(
		    summary = "Updates a match",
		    description = "Updates match entity with the new given fields")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successfully updated a match"),
		    @ApiResponse(responseCode = "404", description = "Cannot find existing match with the given ID", content = { @Content(schema = @Schema()) })})
    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<MatchResource> updateMatch(@io.swagger.v3.oas.annotations.parameters.RequestBody(
		    					content = @Content(schema=@Schema(implementation = MatchResource.class)))
		    @RequestBody MatchResource matchResource) {
	return new ResponseEntity<>(matchService.updateMatch(matchResource), HttpStatus.CREATED);
    }

    @Operation(
		    summary = "Deletes a match",
		    description = "Deletes match entity with the new given id")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successfully deleted a match"),
		    @ApiResponse(responseCode = "404", description = "Cannot find existing match with the given ID", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable(value = "id") long id) {
	matchService.deleteMatch(id);
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
		    summary = "Finds a match",
		    description = "Finds a match for the given Sport and for the given match-date and/or team")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successfully deleted a match"),
		    @ApiResponse(responseCode = "404", description = "Sport not found or at least one of the match-date/team request parameters should be given", content = { @Content(schema = @Schema()) })
    })
    @GetMapping(value = "/findMatch", produces = "application/json")
    public ResponseEntity<List<MatchResource>> findMatchBy(@RequestParam(value = "matchDate", required = false) LocalDate matchDate,
	@RequestParam(value = "team", required = false) String team,
	@RequestParam(value = "sport") String sport) {
	return new ResponseEntity<>(matchService.findMatchesBy(matchDate, team, sport), HttpStatus.OK);
    }

    @Operation(
		    summary = "Fetch match odd by the given ID",
		    description = "fetches a specific match odd entity")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
		    @ApiResponse(responseCode = "404", description = "Cannot find match odd with the given ID", content = { @Content(schema = @Schema()) })})
    @GetMapping(value = "/odds/{id}", produces = "application/json")
    public ResponseEntity<MatchOddResource> getMatchOdd(@PathVariable("id") Long id) {
	return new ResponseEntity<>(matchOddService.findById(id), HttpStatus.OK);
    }

    @Operation(
		    summary = "Adds a match odd",
		    description = "Adds a new match odd entity")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "successfully added a match odd")
    })
    @PostMapping(value = "/odds", produces = "application/json", consumes = "application/json")
    public ResponseEntity<MatchOddResource> createMatchOdd(@io.swagger.v3.oas.annotations.parameters.RequestBody(
		    					content = @Content(schema=@Schema(implementation = MatchOddResource.class)))
		    @RequestBody MatchOddResource matchOddResource) {
	return new ResponseEntity<>(matchOddService.createMatchOdd(matchOddResource), HttpStatus.CREATED);
    }

    @Operation(
		    summary = "Updates a match odd",
		    description = "Updates match odd entity with the new given fields")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successfully updated a match"),
		    @ApiResponse(responseCode = "404", description = "Cannot find existing match odd with the given ID", content = { @Content(schema = @Schema()) })
    })
    @PutMapping(value = "/odds", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MatchOddResource> updateMatchOdd(@io.swagger.v3.oas.annotations.parameters.RequestBody(
		    					content = @Content(schema=@Schema(implementation = MatchOddResource.class)))
		    @RequestBody MatchOddResource matchOddResource) {
	return new ResponseEntity<>(matchOddService.updateMatchOdd(matchOddResource), HttpStatus.OK);
    }

    @Operation(
		    summary = "Deletes a match odd",
		    description = "Deletes match odd entity with the new given id")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successfully deleted a match odd"),
		    @ApiResponse(responseCode = "404", description = "Cannot find existing match odd with the given ID", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/odds")
    public ResponseEntity<Void> deleteMatchOdd(@RequestParam("matchOddId") Long matchOddId) {
	matchOddService.deleteMatchOdd(matchOddId);
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
		    summary = "Finds match odds for a match",
		    description = "Finds all the match odds entities for the given match-id")
    @ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "successful operation"),
		    @ApiResponse(responseCode = "404", description = "Cannot find existing match with the given ID", content = { @Content(schema = @Schema()) })
    })
    @GetMapping(value = "/odds/findForMatch", produces = "application/json")
    public ResponseEntity<List<MatchOddResource>> getMatchOddsByMatch(@RequestParam ("matchId") Long matchId) {
	return new ResponseEntity<>(matchOddService.findMatchOddsByMatchId(matchId), HttpStatus.OK);
    }
}
