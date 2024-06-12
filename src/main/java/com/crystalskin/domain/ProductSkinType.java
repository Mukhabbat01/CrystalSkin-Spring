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

@Entity
@Getter
@Setter
public class ProductSkinType {
	
	@EmbeddedId
	PrdSkinListsId prdSkinListsId;



	@Embeddable
	@Getter
	@Setter

	@NoArgsConstructor
	@AllArgsConstructor
	public static class PrdSkinListsId implements Serializable {
		@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "skin_id")
		private SkinCat skinCat;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "prd_id")
		private Product product;
	}
}
