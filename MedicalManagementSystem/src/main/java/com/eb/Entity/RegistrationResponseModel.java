package com.eb.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponseModel {
	private String message;
	private String name;
	private String userId;

}
