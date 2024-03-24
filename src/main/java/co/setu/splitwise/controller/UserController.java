package co.setu.splitwise.controller;

import co.setu.splitwise.model.RegisteredUser;
import co.setu.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static co.setu.splitwise.util.Util.jsonResponse;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-group/{userId}")
    public ResponseEntity getGroups(@PathVariable String userId) {
        RegisteredUser user = userRepository.getById(userId);
        return jsonResponse(
                "userId", user.getUserId(),
                "groups", user.getGroup()
                );
    }
}
