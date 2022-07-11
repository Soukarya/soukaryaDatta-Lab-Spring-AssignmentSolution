package com.greatlearning.student.studentmanagementsystem.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatlearning.student.studentmanagementsystem.entity.User;
import com.greatlearning.student.studentmanagementsystem.repository.UserRepository;
import com.greatlearning.student.studentmanagementsystem.security.UserDetailsImpl;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		System.out.println("username:" + user.getUsername() + " ----- " + "password: " + user.getPassword());
		return new UserDetailsImpl(user);
	}

}