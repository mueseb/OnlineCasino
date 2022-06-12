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
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            finishedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return finishedPassword;
    }

    public boolean checkHashedPassword(String hash, String pwd, String salt)
    {
         if(getHashedPassword(pwd,salt).equals(hash))
         {
             return true;
         }
         return false;
    }

    public static void main(String[] args) {
        String pwd,salt;
        pwd = "admin";
        salt = "d8gw6b4ghlakg45w8";
        String hashedpwd = "538dff632b1f7621ea20b88d104c41aadb09dcfba467fc36a22c64026dccb495";
        Hash hash = new Hash();
        System.out.println(hash.getHashedPassword(pwd,salt));
        System.out.println(hash.checkHashedPassword(hashedpwd,pwd,salt));
    }

}
