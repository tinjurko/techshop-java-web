package com.controller;

import com.repository.LoginHistoryRepository;
import com.repository.UserRepository;
import com.shop.dbo.LoginHistory;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class Login {

    @NotEmpty
    private String username;

    @Size(min = 5, message = "Password must have at least 5 characters")
    private String password;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private LoginHistoryRepository loginHistoryRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private FacesContext facesContext;

    public void submit() throws IOException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                //externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:

                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setUser(userRepository.find(username));
                loginHistory.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                loginHistory.setIp(getRemoteIpAddress(request));

                loginHistoryRepository.saveLogin(loginHistory);

                externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
                break;
        }
    }
    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(username, password))
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getRemoteIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-FORWARDED-FOR");

        if(ip != null) {
            ip = ip.replaceFirst(",.*", "");
        } else {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}