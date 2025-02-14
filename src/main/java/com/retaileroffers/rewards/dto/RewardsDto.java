package com.retaileroffers.rewards.dto;

import lombok.Data;

import java.util.Map;
@Data
public class RewardsDto {
    String customerName;
    Long customerId;
    Map<String,Integer> rewardPerMonth;
    Integer totalRewards;
}
