package com.capg.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capg.dao.UserDao;
import com.capg.entity.User;
import com.capg.services.UserServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserServiceImp userservice;

	@MockBean
	private UserDao userdao;
	
	
	@Test
	public void getUsersTest() {
		when(userdao.findAll()).thenReturn(Stream
				.of(new User("fs",376, "Abhishek", "Abhishek@123" , "USA","123456789" , "abhi@gmail.com"), new User("cs",958, "Manisha", "Manisha@123", "UK" , "987654321" , "manisha@123")).collect(Collectors.toList()));
		assertEquals(2, ((List<User>) userservice.displayAllUser()).size());
	}
	


	@Test
	public void saveUserTest() {
		User user = new User("fs",999, "Pranya", "Pranya@123", "Pune","45678913","pranaya@gmail.cm");
		when(userdao.save(user)).thenReturn(user);
		assertEquals(user, userservice.createUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User("fs",999, "Pranya", "Pranya@123", "Pune","45678913","pranaya@gmail.cm");
		userservice.deleteUser(user.getUserId());
		verify(userdao, times(1)).delete(user);
	}

}
