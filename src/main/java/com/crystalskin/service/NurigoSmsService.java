package com.crystalskin.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crystalskin.config.NurigoP;
import com.crystalskin.domain.Phone;
import com.crystalskin.dto.NurigoSmsDto;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class NurigoSmsService {
final DefaultMessageService messageService;
	
	private final NurigoP nurigo;

	@Autowired
    public NurigoSmsService(NurigoP nurigo) {
		this.nurigo = nurigo;
		this.messageService = NurigoApp.INSTANCE.initialize(this.nurigo.getApikey(),this.nurigo.getApisecret(), "https://api.solapi.com");
    }


	public String createRandomNumber(NurigoSmsDto nurigoSmsDto) {
		Phone phone = nurigoSmsDto.smsDtoToEntity();
		Random rand = new Random();
		String randomCode = "";
		for (int i = 0; i < 4; i++) {
			String random = Integer.toString(rand.nextInt(10));
			randomCode += random;
		}
		 nurigoSmsDto.setCode(randomCode);
		 return nurigoSmsDto.getCode();
	}
		


	public boolean sendOne(NurigoSmsDto nurigoSmsDto) {
        String randomCode = createRandomNumber(nurigoSmsDto);
        Message message = new Message();
        message.setFrom(this.nurigo.getFromnumber());
        message.setTo(nurigoSmsDto.getPhone());
        message.setText("인증코드: " + randomCode);
        System.out.println(randomCode);
        
        try {
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            // Check response status and return true if successful
            return response.getStatusCode().equals("2000");
        } catch (Exception e) {
            System.err.println("Error sending SMS: " + e.getMessage());
            return false;
        }
    }


}
