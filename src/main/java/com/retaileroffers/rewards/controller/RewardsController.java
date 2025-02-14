package com.retaileroffers.rewards.controller;

import com.retaileroffers.rewards.dto.RewardsDto;
import com.retaileroffers.rewards.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
@Tag(name = "Rewards API", description = "Endpoints for customer rewards")
public class RewardsController {

    private final RewardService rewardService;

    public RewardsController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get Reward Points for a Customer", description = "Fetches reward points by customer ID")
    public ResponseEntity<RewardsDto> getRewards(@PathVariable Long customerId) {
        RewardsDto rewards = rewardService.getRerwards(customerId);
        if (rewards == null) {
            throw new RuntimeException("Customer not found or has no rewards");
        }
        return ResponseEntity.ok(rewards);
    }
}
