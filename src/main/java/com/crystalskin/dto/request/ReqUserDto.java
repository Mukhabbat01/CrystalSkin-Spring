package com.crystalskin.dto.request;

import com.crystalskin.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUserDto {
	
	private String usrId;
	private String usrName;
	private String usrBirth;
	private String gender;
	private String email;
	private String pass;
	private String phone;
	
	public User userDtoToEntity() {
		User user = new User();
		user.setUsrId(this.usrId);
		user.setUsrName(this.usrName);
		user.setUsrBirth(this.usrBirth);
		user.setGender(this.gender);
		user.setEmail(this.email);
		user.setPass(this.pass);
		user.setPhone(this.phone);
		return user;
	}
}
