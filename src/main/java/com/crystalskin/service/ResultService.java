package com.crystalskin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crystalskin.domain.Result;
import com.crystalskin.domain.SkinCat;
import com.crystalskin.domain.User;
import com.crystalskin.dto.response.ResultResDto;
import com.crystalskin.repository.ResultRepository;
import com.crystalskin.repository.SkinCatRepository;
import com.crystalskin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final SkinCatRepository skinCatRepository;
    private final UserRepository userRepository;

    public Result saveResult(String result, String usrId) {
        // Check if the user exists
        Optional<User> optionalUser = userRepository.findById(usrId);
        
        // If user exists, update the existing record, else create a new record
        if (optionalUser.isPresent()) {
            // User exists, update the existing record
            User user = optionalUser.get();
            
            // Retrieve the existing result (if any)
            Result resultEntity = resultRepository.findByUser(user);
            if (resultEntity == null) {
                resultEntity = new Result();
                resultEntity.setUser(user);
            }
            
            SkinCat skin = skinCatRepository.findBySkinTypeContainsIgnoreCase(result);
            resultEntity.setSkinCat(skin);
            
            return resultRepository.save(resultEntity);
        } else {
            // User doesn't exist, create a new record
            User user = new User();
            user.setUsrId(usrId);
            
            Result resultEntity = new Result();
            resultEntity.setUser(user);
            
            SkinCat skin = skinCatRepository.findBySkinTypeContainsIgnoreCase(result);
            resultEntity.setSkinCat(skin);
            
            return resultRepository.save(resultEntity);
        }
    }

   public List<ResultResDto> showResult(String usrId) {
	  return  resultRepository.selectById(usrId);
   }
   
  
}
 