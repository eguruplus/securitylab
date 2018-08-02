import java.util.*;
public class playfair {
    public static void main(String s[]){
        Scanner inp=new Scanner(System.in);
        char[][] m=new char[5][5];
        int i,j,k=0;
        String key="";
        System.out.print("Enter Key : ");
        key=inp.nextLine();
        key=fillmatrix(key.toLowerCase()); //Remove duplicates from the key
        System.out.println("Key Matrix");
        for(i=0;i<5;++i){
            for(j=0;j<5;++j){
                m[i][j]=key.charAt(k);
                k++;
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        StringBuffer plaintext;
        Vector<Integer> vec=new Vector();
        System.out.print("Enter Plaintext : ");
        plaintext=new StringBuffer(inp.nextLine());
        for(i=1;i<plaintext.length();i+=2){
            if(plaintext.charAt(i)==plaintext.charAt(i-1)){
                plaintext.insert(i, 'x');
                vec.addElement(i);
            }
        }
        if(plaintext.length()%2!=0){
                plaintext.append('x');
                vec.addElement(plaintext.length()-1);
        }
        System.out.println("Filler Added Plaintext : "+plaintext);
        String cipher="";
        StringBuffer str;
        for(i=0;i<plaintext.length();i+=2){
            str=new StringBuffer(plaintext.substring(i, i+2));
            cipher+=encrypt(str.toString(),m);
        }
        System.out.println("Encryption : "+cipher);
        String decipher="";
        for(i=0;i<cipher.length();i+=2){
            str=new StringBuffer(cipher.substring(i, i+2));
            decipher+=decrypt(str.toString(),m);
        }
        Iterator<Integer> itr=vec.iterator();
        str=new StringBuffer(decipher);
        i=0;
        while(itr.hasNext()){
            str.deleteCharAt(itr.next()-i);
            ++i;
        }
        System.out.println("Decryption : "+str);
    }
    
    static String fillmatrix(String key){
        LinkedHashSet<Character> fillkey=new LinkedHashSet();
        int i;
        for(i=0;i<key.length();++i){
            if(key.charAt(i)=='j'){
                fillkey.add('i');
            }
            fillkey.add(key.charAt(i));
        }
        for(i=97;i<=122;++i){
            fillkey.add((char)i);
        }
        fillkey.remove('j');
        key="";
        Iterator<Character> itr=fillkey.iterator();
        while(itr.hasNext()){
            key+=itr.next().toString();
        }
        return key;
    }
    static String encrypt(String str,char[][] m){
        StringBuffer cipher=new StringBuffer();
        int[][] pos=new int[2][2];
        int i;
        pos=position(str,m);
        if(pos[0][0]==pos[1][0]){
            for(i=0;i<2;++i)
                cipher.append(m[pos[0][0]][(pos[i][1]+1)%5]);
        }
        else if(pos[0][1]==pos[1][1]){
            for(i=0;i<2;++i)
                cipher.append(m[(pos[i][0]+1)%5][pos[0][1]]);
        }
        else{
            for(i=0;i<2;++i)
                cipher.append(m[pos[i][0]][pos[(i+1)%2][1]]);
        }
        return cipher.toString();
    }
    static String decrypt(String str,char[][] m){
        StringBuffer decipher=new StringBuffer();
        int[][] pos=new int[2][2];
        int i;
        pos=position(str,m);
        if(pos[0][0]==pos[1][0]){
            for(i=0;i<2;++i)
                decipher.append(m[pos[0][0]][(pos[i][1]+4)%5]);
        }
        else if(pos[0][1]==pos[1][1]){
            for(i=0;i<2;++i)
                decipher.append(m[(pos[i][0]+4)%5][pos[0][1]]);
        }
        else{
            for(i=0;i<2;++i)
                decipher.append(m[pos[i][0]][pos[(i+1)%2][1]]);
        }
        return decipher.toString();
    }
    static int[][] position(String str,char[][] m){
        int[][] pos=new int[2][2];
        int i,j,flag=0;
        for(i=0;i<5;++i){
            for(j=0;j<5;++j){
                if(m[i][j]==str.charAt(0)){
                    pos[0][0]=i;
                    pos[0][1]=j;
                    flag++;
                }
                else if(m[i][j]==str.charAt(1)){
                    pos[1][0]=i;
                    pos[1][1]=j;
                    flag++;
                }
                if(flag==2)
                    break;
            }
        }
        return pos;
    }
}
