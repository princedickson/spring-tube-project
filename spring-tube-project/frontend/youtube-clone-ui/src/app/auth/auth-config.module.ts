import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';

@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-6bhhk6qnv47byn0d.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'jmX56uLu67ZYzPX01L7cwq91i7lXkuVR',
            scope: 'openid profile offline_access',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
