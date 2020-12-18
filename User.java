package ca.sheridancollege.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	private Long userId;
	@NonNull
	private String email; // actual login name
	@NonNull
	private String encryptedPassword;
	@NonNull
	private Boolean enabled;

	private String password;

	private Integer role;

}
