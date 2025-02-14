package com.retaileroffers.rewards.service;

import com.retaileroffers.rewards.dto.RewardsDto;
import com.retaileroffers.rewards.entity.Transaction;

public interface RewardService {
    RewardsDto getRerwards(Long cusromerId);
    int calculateRewards(double amount);
}
