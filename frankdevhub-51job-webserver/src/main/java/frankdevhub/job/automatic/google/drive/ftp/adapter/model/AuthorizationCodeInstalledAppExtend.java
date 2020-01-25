package frankdevhub.job.automatic.google.drive.ftp.adapter.model;

import java.io.IOException;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;

/**
 * <p>Title:AuthorizationCodeInstalledAppExtend.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-28 16:00
 */

public class AuthorizationCodeInstalledAppExtend extends AuthorizationCodeInstalledApp{

	private static AuthorizationCodeRequestUrl requestUrl;
 	
	public AuthorizationCodeInstalledAppExtend(AuthorizationCodeFlow flow, VerificationCodeReceiver receiver) {
		super(flow, receiver);
	}


	@Override
	public Credential authorize(String userId) throws IOException {
		try {
			Credential credential = getFlow().loadCredential(userId);
			if (credential != null && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() == null
					|| credential.getExpiresInSeconds() > 60)) {
				return credential;
			}
		
			String redirectUri = getReceiver().getRedirectUri();
			AuthorizationCodeRequestUrl authorizationUrl = getFlow().newAuthorizationUrl().setRedirectUri(redirectUri);
			requestUrl = authorizationUrl;
			onAuthorization(authorizationUrl);
	
			String code = getReceiver().waitForCode();
			TokenResponse response = getFlow().newTokenRequest(code).setRedirectUri(redirectUri).execute();
		
			return getFlow().createAndStoreCredential(response, userId);
		} finally {
			getReceiver().stop();
		}
	}


	public static AuthorizationCodeRequestUrl getRequestUrl() {
		return requestUrl;
	}

}
