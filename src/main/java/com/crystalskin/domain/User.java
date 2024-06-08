package com.crystalskin.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@Column(length = 50, nullable = false)
	private String usrId;

	@Column(length = 50, nullable = false)
	private String usrName;

	@Column(length = 10, nullable = false)
	private String usrBirth;

	@Column(length = 1, nullable = false)
	private String gender;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 20, nullable = false)
	private String pass;

	@Column(length = 11, nullable = false)
	private String phone;

//	@ManyToOne
//	@JoinColumn(name = "skin_id")
//	private SkinCat skinCat;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Result> results;

}
