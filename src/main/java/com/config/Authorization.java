package com.config;

import com.repository.UserRepository;
import com.shop.dbo.User;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.HashSet;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                useForwardToLogin = false
        )
)
@FacesConfig
@ApplicationScoped
public class Authorization implements IdentityStore {

    @Inject
    UserRepository userRepository;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        System.out.println(((UsernamePasswordCredential) credential).getCaller());
        User user = userRepository.find(login.getCaller());

        if(user != null && user.getPassword().equals(login.getPasswordAsString()) && user.getRole().equals("user")) {
            return new CredentialValidationResult(user.getUsername(), new HashSet<>(Collections.singletonList("USER")));
        } else if(user != null && user.getPassword().equals(login.getPasswordAsString()) && user.getRole().equals("admin")) {
            return new CredentialValidationResult(user.getUsername(), new HashSet<>(Collections.singletonList("ADMIN")));
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
