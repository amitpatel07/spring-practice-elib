package com.elibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elibrary.data.model.Address;
import com.elibrary.data.model.Person;
import com.elibrary.data.model.User;
import com.elibrary.data.repository.UserRepository;
import com.elibrary.data.view.Response;
import com.elibrary.data.view.UserView;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepository;

	@Transactional
	public Response registerUser(UserView userview) {
		String message = null;
		String status = "failure";
		if (userview != null) {
			String name = userview.getName();
			Integer age = userview.getAge();
			String contact = userview.getNumber();
			String username = userview.getUsername();
			String password = userview.getPassword();
			String email = userview.getEmail();
			String role = userview.getRole();

			Person person = new Person(null, name, age, contact);
			User user = new User(person, username, password, email, role);
			userrepository.addUser(user);
			message = "user added succesfully";
			status = "success";
		}

		return new Response(message, status);
	}

	public UserView getUser(String userName) {
		User user = userrepository.getUser(userName);
		UserView view =null;
		if(user!=null ) {
			view=new UserView();
			view.setAge(user.getPerson().getAge());
			view.setEmail(user.getEmail());
			view.setName(user.getPerson().getName());
			view.setNumber(user.getPerson().getContactNo());
			view.setRole(user.getRole());
			view.setUsername(user.getUsername());
			view.setUserId(user.getId());
			List<Address> addresses= user.getPerson().getAddresses();
			if(addresses!=null &&!addresses.isEmpty()) {
				List<String> adList=new ArrayList<String>();
				for (Address address : addresses) {
					String adr=address.getAddress();
					adList.add(adr);
				}
				view.setAddress(adList);
			}
		}
		return view;
		
	}
	
}
