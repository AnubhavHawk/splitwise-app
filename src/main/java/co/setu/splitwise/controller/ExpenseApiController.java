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

        return null;
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
        Expense created = expenseService.addExpense(addExpenseDto);
        return jsonResponse("expenseId", created.getExpenseId());
    }

    @PutMapping("/update")
    public ResponseEntity updateExpense(@RequestBody UpdateExpenseDto updateExpenseDto) {

        return null;
    }

    @DeleteMapping("/delete")
    ResponseEntity deleteExpense(@RequestBody DeleteExpenseDto deleteExpenseDto) {

        return null;
    }
}