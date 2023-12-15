package team.challenge.MobileStore.security.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import team.challenge.MobileStore.config.AppProperties;
import team.challenge.MobileStore.exception.BadRequestException;
import team.challenge.MobileStore.security.TokenProvider;
import team.challenge.MobileStore.util.CookieUtil;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static team.challenge.MobileStore.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String targetUrl = determinateTargetUrl(request, response, authentication);
        if (response.isCommitted()){
            logger.debug(String.format("Response has already been committed. Unable to redirect to %s", targetUrl));
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determinateTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        Optional<String> redirectUri = CookieUtil.getCookie(request,
                REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())){
            throw new BadRequestException("Sorry! we've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        String token = tokenProvider.createToken(authentication);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String url){
        URI clientRedirectUri = URI.create(url);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(auth -> {
                    URI authorizedURI = URI.create(auth);
                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) &&
                            authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    } return false;
                });
    }
}
