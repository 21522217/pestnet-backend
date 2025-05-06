package uit.app.com.pestnet.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uit.app.com.pestnet.dto.UserRequestDTO;
import uit.app.com.pestnet.dto.UserResponseDTO;
import uit.app.com.pestnet.mapper.UserMapper;
import uit.app.com.pestnet.model.User;
import uit.app.com.pestnet.repository.UserRepository;
import uit.app.com.pestnet.service.UserService;

import java.util.List;
import java.util.UUID;

/**
 * @author trong-khiem
 */
@Service
public class UserServiceImpl implements UserService {

 private final UserRepository userRepository;
 private final UserMapper userMapper;
 private final PasswordEncoder passwordEncoder;

 public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
  this.userRepository = userRepository;
  this.userMapper = userMapper;
  this.passwordEncoder = passwordEncoder;
 }

 @Override
 public User getUserById(UUID id) {
  return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
 }

 @Override
 public List<User> getAllUsers() {
  return userRepository.findAll();
 }

 @Override
 public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
  User user = userMapper.toEntity(userRequestDTO);
  user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
  user.setRole("user");
  user.setDeleted(false);
  return userMapper.toResponseDTO(userRepository.save(user));
 }
}