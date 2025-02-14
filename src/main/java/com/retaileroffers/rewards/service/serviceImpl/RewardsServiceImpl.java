package com.retaileroffers.rewards.service.serviceImpl;

import com.retaileroffers.rewards.dto.CustomerDto;
import com.retaileroffers.rewards.dto.RewardsDto;
import com.retaileroffers.rewards.entity.Transaction;
import com.retaileroffers.rewards.exception.TransactionNotFoundException;
import com.retaileroffers.rewards.repository.TransactionRepository;
import com.retaileroffers.rewards.service.CustomerService;
import com.retaileroffers.rewards.service.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RewardsServiceImpl implements RewardService {

    TransactionRepository transactionRepository;
    CustomerService customerService;
    /*
        Calculate reward earned per month and total points
     */
    @Override
    public RewardsDto getRerwards(Long cusromerId) {
        RewardsDto rewardsDto=new RewardsDto();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        CustomerDto customer=customerService.getCustomerById(cusromerId);
        List<Transaction> transactions=transactionRepository.getByCustomerId(cusromerId);
        if(transactions.isEmpty())
            throw new TransactionNotFoundException("No transaction not found for customer" +customer.getName());
        Map<String,Integer> rewardPerMonth=transactions.stream()
                 .collect(Collectors.groupingBy(
                      t -> t.getTransactionDate().format(monthFormatter),
                        Collectors.summingInt(Transaction::getPoints)
        ));
        rewardsDto.setRewardPerMonth(rewardPerMonth);
        rewardsDto.setTotalRewards(transactions.stream().mapToInt(Transaction::getPoints).sum());
        rewardsDto.setCustomerId(cusromerId);
        rewardsDto.setCustomerName(customer.getName());
        return rewardsDto;
    }

    @Override
    public int calculateRewards(double amount){
            int points = 0;
            if (amount > 100) {
                points += (int) ((amount - 100) * 2);
                amount = 100;
            }

            if (amount > 50) {
                points += (int) (amount - 50);
            }
             return points;

    }
}
