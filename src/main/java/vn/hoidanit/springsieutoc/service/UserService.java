package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.User;
import vn.hoidanit.springsieutoc.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<User> fetchUsers() {
		// Logic fetch user/kết nối DB thực tế sẽ ở đây
		List<User> userList = this.userRepository.findAll();
		return userList;
	}

	public void createUser(User user) {
		String hashPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		this.userRepository.save(user);
	}

	public void updateUser(User inputUser) {
		User currentUserInDB = this.findUserById(inputUser.getId());
		if (currentUserInDB != null) {
			currentUserInDB.setName(inputUser.getName());
			currentUserInDB.setEmail(inputUser.getEmail());
			currentUserInDB.setAddress(inputUser.getAddress());

			this.userRepository.save(currentUserInDB);
		}
	}

	public void deleteUserById(int id) {
		this.userRepository.deleteById(id);
	}

	public User findUserByEmail(String email) {
		Optional<User> userOpt = this.userRepository.findByEmail(email);
		if (!userOpt.isPresent())
			return null;
		return userOpt.get();
	}
}