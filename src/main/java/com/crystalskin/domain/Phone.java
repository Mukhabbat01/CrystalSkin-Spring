package com.crystalskin.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Phone {
	@Id
	@Column(length=11, nullable=false)
	private String phone;
	
	@Column(length=4, nullable=false)
	private String code;
	
	
	private LocalDateTime expiresAt;
	
	@PrePersist
    public void setDefaultValues() {
        if (expiresAt == null) {
            expiresAt = LocalDateTime.now().plusMinutes(3); // 현재 시간에서 3분을 더한 값
        }
    }
}
