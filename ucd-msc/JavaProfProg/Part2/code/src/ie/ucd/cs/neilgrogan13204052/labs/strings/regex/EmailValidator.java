package ie.ucd.cs.neilgrogan13204052.labs.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class EmailValidator {

    public static void main(String[] args) {
        String email = "";
        if(args.length < 1){
            System.out.println("Command Syntax: java EmailValidator <emailAddress>");
            System.exit(0);
        }else{
            email = args[0];
        }
        Pattern p = Pattern.compile("^\\.+|^\\@+");
        Matcher m = p.matcher(email);
        if(m.find()){
            System.err.println("Invalid Email Address: starts with a . or @ sign");
            System.exit(-1);
        }
        // Look for email addresses that start with www.
        p = Pattern.compile("^www\\.");
        m = p.matcher(email);
        if(m.find()){
            System.err.println("Invalid Email Address: starts www");
            System.exit(-1);
        }


        p = Pattern.compile("[^A-Za-z0-9\\@\\.\\_]]");
        m = p.matcher(email);
        if(m.find()){
            System.err.println("Invalid Email Address: contains invalid characters");
            System.exit(-1);
        }else{
            System.out.println(args[0] + " is a valid email address.");
        }
    }
}
