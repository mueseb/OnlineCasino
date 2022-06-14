/*
 * @author: Sebastian Münzer & Armin Hartner
 * @date: 24.05.2022
 * @project-name: Online Casino
 */
package at.kaindorf.onlinecasino.db;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public String getHashedPassword(String pwd, String salt)
    {
        String finishedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pwd.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : bytes) {
                sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
            finishedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return finishedPassword;
    }

    public boolean checkHashedPassword(String hash, String pwd, String salt)
    {
        return getHashedPassword(pwd, salt).equals(hash);
    }
}
