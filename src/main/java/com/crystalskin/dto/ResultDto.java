package com.crystalskin.dto;

import com.crystalskin.domain.Result;
import com.crystalskin.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto {
    private String mbti;
    private String usrId;


    public Result resultToEntity() {
    	Result result = new Result();
    	User user = new User();
        user.setUsrId(usrId);
        result.setUser(user);
		return result;
        
    }
}
