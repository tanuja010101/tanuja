package com.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Menu {
	public Menu(Long id, @NonNull @NotEmpty(message = "'name' field was empty") String name,
			@NonNull @NotEmpty(message = "'description' field was empty") String description,
			@NonNull @NotEmpty(message = "'additional' field was empty") String additional,
			@NonNull @NotEmpty(message = "'image' field was empty") String image, int price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.additional = additional;
		this.image = image;
		this.price = price;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@NonNull
	@NotEmpty(message = "'name' field was empty")
	@Column(name = "name", nullable = false)
	private String name;

	@NonNull
	@NotEmpty(message = "'description' field was empty")
	@Column(name = "description", nullable = false)
	private String description;

	@NonNull
	@NotEmpty(message = "'additional' field was empty")
	@Column(name = "additional", nullable = false)
	private String additional;

	@NonNull
	@NotEmpty(message = "'image' field was empty")
	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "price", nullable = false)
	private int price;

}
