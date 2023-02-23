
export interface SignUpResponse {
    accessToken(accessToken: any): unknown;
    using2FA: boolean;
    qrCodeImage: string;
  }