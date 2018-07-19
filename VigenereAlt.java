import java.util.*;
public class VigenereAlt{
	public static void main(String a[])
	{
		String cipher,plain,key;
		Scanner scn=new Scanner(System.in);
		System.out.println("Enter Plain text : ");
		plain=scn.nextLine().toUpperCase();
		System.out.println("Enter Key : ");
		key=scn.nextLine().toUpperCase();
		cipher="";
		int i,j,k;
		j=0;
		for(i=0;i<plain.length();++i)
		{
			k=plain.charAt(i)+key.charAt(j);
			++j;
			if(k>155)
				cipher+=(char)(k-'A'-26);
			else
				cipher+=(char)(k-'A');
			if(j==key.length())
				j=0;
			
		}
		System.out.println(" Encryption : "+cipher);
		
		String decipher="";
		j=0;
		for(i=0;i<cipher.length();++i)
		{
			k=cipher.charAt(i)-key.charAt(j);
			++j;
			if(k>=0)
				decipher+=(char)(k+'A');
			else
				decipher+=(char)(k+'A'+26);
			if(j==key.length())
				j=0;
		}
		System.out.println(" Decryption : "+decipher);
	}
}
