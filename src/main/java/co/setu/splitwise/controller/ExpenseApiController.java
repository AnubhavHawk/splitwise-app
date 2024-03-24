package co.setu.splitwise.controller;

import co.setu.splitwise.dto.expense.AddExpenseDto;
import co.setu.splitwise.dto.expense.DeleteExpenseDto;
import co.setu.splitwise.dto.expense.UpdateExpenseDto;
import co.setu.splitwise.model.Expense;
import co.setu.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static co.setu.splitwise.util.Util.jsonResponse;

@RestController("expense")
public class ExpenseApiController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/get-for-user/{userId}")
    public ResponseEntity getExpenseForUser(@PathVariable String userId) {
        return jsonResponse(
                "userId", userId,
                "expenses", expenseService.getExpenseForUser(userId));
    }

    /**
     * Description
     * Amount
     * splitBetween - List(UserId)
     * CreatedBy
     * groupId - If from a group
     * */
    @PostMapping("/add")
    public ResponseEntity addExpense(@RequestBody AddExpenseDto addExpenseDto) {
        try {
            Expense created = expenseService.addExpense(addExpenseDto);
            return jsonResponse("expenseId", created.getExpenseId());
        }
        catch (IllegalArgumentException ex) {
            return jsonResponse(
                    "error", ex.getMessage().trim(),
                    "success", false
            );
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateExpense(@RequestBody UpdateExpenseDto updateExpenseDto) {
        try {
            Expense updated = expenseService.updateExpense(updateExpenseDto);
            return jsonResponse(
                    "expenseId", updated.getExpenseId(),
                    "status", updated.getStatus()
            );
        }
        catch (IllegalArgumentException ex) {
            return jsonResponse(
                    "error", ex.getMessage().trim(),
                    "success", false
            );
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity deleteExpense(@RequestBody DeleteExpenseDto deleteExpenseDto) {

        return null;
    }
}