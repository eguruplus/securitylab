import java.io.*;
class ceaser
{

    static String encrypt(String pt, int key) 
    {
               String ct="";

        for (int i = 0; i < pt.length(); i++)
        {

            char letter = pt.charAt(i);
            letter = (char) (letter + key);
            if (letter > 'z') 
            {
                letter = (char) (letter - 26);
            } 
            ct+= letter;
        }
       return(ct);
    }


static String decrypt(String ct, int key) 
{
       String pt="";
        for (int i = 0; i < ct.length(); i++) 
        {
           char letter = ct.charAt(i);
            letter = (char) (letter - key);
            if (letter < 'a') 
            {
                letter = (char) (letter + 26);
            }
            pt+=letter;
        }
        return(pt);
    }
    public static void main(String[] args) {

        String a = "guru";
        System.out.println(a);
        String ct = encrypt(a, 1);
        String pt = decrypt(ct, 1);
        System.out.println("cipher text="+ct);
        System.out.println("plain text="+pt);
    }
}
