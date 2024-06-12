package com.crystalskin.domain;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
	@EmbeddedId
	WishListsId wishListsId;



	@Embeddable
	@Getter
	@Setter

	@NoArgsConstructor
	@AllArgsConstructor
	public static class WishListsId implements Serializable {
		@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//		@OnDelete(action = OnDeleteAction.CASCADE)
		@JoinColumn(name = "usr_id")
		private User user;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "prd_id")
		private Product product;
	}

}
