package com.bosssoft.trainee.auth.server.integration;

public class LoginAuthenticationContext {

    private static ThreadLocal<LoginAuthParamDTO> holder = new ThreadLocal<>();

    public static void set(LoginAuthParamDTO integrationAuthentication) {
        holder.set(integrationAuthentication);
    }

    public static LoginAuthParamDTO get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}