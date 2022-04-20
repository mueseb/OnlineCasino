package at.kaindorf.onlinecasino.frontend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginData {
    private String name, pwd;

    @Override
    public String toString() {
        return name + ";" + pwd;
    }
}
