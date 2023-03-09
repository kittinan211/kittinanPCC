package com.pccth.jwt;

public class AuthResponse {
	    private String name;
	    private String email;
	    private String secret;
	    private String accessToken;
	    private boolean authenticated;
	 
	    public AuthResponse() { }

		public AuthResponse(String name, String email, String secret, String accessToken, boolean authenticated) {
			super();
			this.name = name;
			this.email = email;
			this.secret = secret;
			this.accessToken = accessToken;
			this.authenticated = authenticated;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public boolean isAuthenticated() {
			return authenticated;
		}

		public void setAuthenticated(boolean authenticated) {
			this.authenticated = authenticated;
		}

	
	 
}
