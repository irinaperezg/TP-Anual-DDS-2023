package server.handlers;

import io.javalin.Javalin;

import java.nio.file.AccessDeniedException;

public class AccessDeniedHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, ctx) -> {
           ctx.redirect("/login");
        });
    }

}
