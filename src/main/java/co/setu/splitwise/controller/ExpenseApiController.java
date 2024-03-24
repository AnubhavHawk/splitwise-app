package co.setu.splitwise.controller;

import co.setu.splitwise.dto.expense.AddExpenseDto;
import co.setu.splitwise.dto.expense.DeleteExpenseDto;
import co.setu.splitwise.dto.expense.UpdateExpenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("expense")
public class ExpenseApiController {

    @GetMapping("/get-for-user/{userId}")
    public ResponseEntity getExpenseForUser(@PathVariable String userId) {

        return null;
    }

    @PostMapping("/add")
    public ResponseEntity addExpense(@RequestBody AddExpenseDto addExpenseDto) {

        return null;
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