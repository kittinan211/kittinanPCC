export interface AuthResponse {
  name: string;
  email: string;
  secret: string;
  accessToken: string;
  authenticated: boolean;

}
