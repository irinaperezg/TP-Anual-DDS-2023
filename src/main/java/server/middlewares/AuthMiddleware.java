package server.middlewares;

import io.javalin.config.JavalinConfig;
import server.exceptions.AccessDeniedException;

import java.util.Arrays;
import java.util.List;

public class AuthMiddleware {
    public static void apply(JavalinConfig config) {
        List<String> allowedPaths = Arrays.asList("/login", "/signup");

        config.accessManager((handler, context, routerRoles) ->
        {
            if (context.sessionAttribute("usuario_id") == null && !allowedPaths.contains(context.path())) {
                context.redirect("/login");
            } else {
                handler.handle(context);
            }
        });
    }
}
