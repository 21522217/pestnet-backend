package uit.app.com.pestnet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uit.app.com.pestnet.dto.UserRequestDTO;
import uit.app.com.pestnet.dto.UserResponseDTO;
import uit.app.com.pestnet.model.User;
import uit.app.com.pestnet.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * @author trong-khiem
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

 private final UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }

 @PostMapping
 public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
  return ResponseEntity.ok(userService.createUser(userRequestDTO));
 }

 @GetMapping("/{id}")
 public ResponseEntity<User> getUser(@PathVariable UUID id) {
  return ResponseEntity.ok(userService.getUserById(id));
 }

 @GetMapping
 public ResponseEntity<List<User>> getAllUsers() {
  return ResponseEntity.ok(userService.getAllUsers());
 }
}
