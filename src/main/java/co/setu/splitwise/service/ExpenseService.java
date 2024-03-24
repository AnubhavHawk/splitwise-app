package co.setu.splitwise.service;

import co.setu.splitwise.dto.expense.AddExpenseDto;
import co.setu.splitwise.model.Expense;
import co.setu.splitwise.model.ExpenseStatus;
import co.setu.splitwise.model.RegisteredUser;
import co.setu.splitwise.model.SplitBetween;
import co.setu.splitwise.repository.ExpenseRepository;
import co.setu.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static co.setu.splitwise.util.RandomIdGenerator.generateRandomId;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;


    @Autowired
    private UserRepository userRepository;

    public Expense addExpense(AddExpenseDto addExpenseDto) {
        // Step 1: Validate the values

        // Step 2: Prepare the expense
        Expense preparedExpense = validateAndPrepareExpenseObject(addExpenseDto);
        if(preparedExpense != null) {
            return expenseRepository.save(preparedExpense);
        }
        // Step 3: Persist to DB
        return null;
    }

    private Expense validateAndPrepareExpenseObject(AddExpenseDto expenseDto) {
        Expense expenseObject = null;
        String validationMessage = "";
        Expense.ExpenseBuilder expenseBuilder = Expense.builder();
        if(expenseDto == null) {
            validationMessage = "\nExpense Object can not be null";
        }
        RegisteredUser createdBy = userRepository.getById(expenseDto.getCreatedBy());
        if(createdBy == null) {
            validationMessage += "\ncreatedBy user is not registered";
        }
        if(expenseDto.getSplitBetween() == null || expenseDto.getSplitBetween().size() == 0) {
            validationMessage += "\nsplitBetween can not be empty";
        }
        if(!validationMessage.equals("")) {
            throw new IllegalArgumentException(validationMessage);
        }
        else { // All Validations are complete
            List<RegisteredUser> registeredUserListToParticipateInSplitting = new ArrayList<>();
            for(String splitBetweenUserId: expenseDto.getSplitBetween()) {
                RegisteredUser user = userRepository.getById(splitBetweenUserId);
                if(user == null) {
                    validationMessage += "\nuser: " + splitBetweenUserId + " is not registered";
                }
                registeredUserListToParticipateInSplitting.add(user);
            }
            if(registeredUserListToParticipateInSplitting.size() == expenseDto.getSplitBetween().size()) { // Only if all the users are registered on splitwise
                String groupId = expenseDto.getGroupId();

                expenseBuilder.expenseId(generateRandomId());
                expenseBuilder.description(expenseDto.getDescription());
                expenseBuilder.totalAmount(expenseDto.getAmount());
                expenseBuilder.createdBy(createdBy);
                expenseBuilder.status(ExpenseStatus.PENDING);
                addUsersToExpense(expenseBuilder, registeredUserListToParticipateInSplitting, groupId);


                expenseObject = expenseBuilder.build(); // Population complete
            }
        }

        return expenseObject;
    }

    private void addUsersToExpense(Expense.ExpenseBuilder expenseBuilder, List<RegisteredUser> splitBetweenUserList, String groupId) {
        List<SplitBetween> preparedSplit = new ArrayList<>();
        for(RegisteredUser user: splitBetweenUserList) {
            preparedSplit.add(getSplit(user, expenseBuilder.build(), splitBetweenUserList.size(), groupId));
        }
        expenseBuilder.splitBetween(preparedSplit);
    }

    private SplitBetween getSplit(RegisteredUser user, Expense expense, int userCount, String groupId) {
        SplitBetween.SplitBetweenBuilder splitBetween = SplitBetween.builder();
        splitBetween.splitId(generateRandomId())
                .expense(expense)
                .amountPerUser(SplitService.calculateSplit(expense.getTotalAmount(), userCount))
                .toBePaidBy(user)
                .groupId(groupId)
                .status(ExpenseStatus.Status.UNPAID)
                .build();
        return splitBetween.build();
    }
}