/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 20.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.web.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {
    int id;
    private String username, pwd;
}
