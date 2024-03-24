package co.setu.splitwise.service;

import org.springframework.stereotype.Service;

@Service
public class SplitService {

    public static double calculateSplit(Double totalAmount, int totalUsers) {
        return totalAmount/totalUsers;
    }
}
