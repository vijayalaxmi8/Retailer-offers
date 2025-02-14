package com.retaileroffers.rewards.service;

import com.retaileroffers.rewards.dto.RewardsDto;

public interface RewardService {
    RewardsDto getRewards(Long cusromerId);
    int calculateRewards(double amount);
}
