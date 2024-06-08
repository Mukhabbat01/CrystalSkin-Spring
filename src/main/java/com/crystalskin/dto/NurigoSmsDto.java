package com.crystalskin.dto;

import com.crystalskin.domain.Phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NurigoSmsDto {
	private String phone;
	private String code;
	
	public Phone smsDtoToEntity() {
		Phone phone = new Phone();
		phone.setCode(this.code);
		phone.setPhone(this.phone);
		return phone;
	}
}
