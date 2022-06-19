/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 20.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.web.auth;

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface JWTNeeded {
}
