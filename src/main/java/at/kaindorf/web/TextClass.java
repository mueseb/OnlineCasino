/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */

package at.kaindorf.web;

import at.kaindorf.web.resource.LoginRescource;

public class TextClass {

    public boolean checkUserForIllegalChars(String userData)
    {
        return !userData.matches("^[a-zA-Z0-9]*$");
    }

    public static void main(String[] args) {
        TextClass loginRescource = new TextClass();
        System.out.println(loginRescource.checkUserForIllegalChars("Test123;"));
    }
}
