packagecom.example.med;

importjava.lang.*;
import java.io.*;
importjava.util.*;


public class RC5d
 {
private final int W = 32; //Word size   
private final intPw = 0xb7e15163; //Magic constant Pw
private final intQw = 0x9e3779b9; //Magic constant Qw
private final int b = 16;//size of encryption/decryption key
private final int u = 4;//number of bytes in w
private final int c = 4;//max of 1 or b
privateint rounds;     //number of round
privateint t;          //size of S = 2(rounds + 1)
privateint CT[]; 
privateint PT[];     


  /*Creates a new RC5 object */

public RC5d()
  {
rounds = 12;
   t = 2 * (rounds + 1);
   CT=new int[2];
   PT=new int[2];
  }
private  StringintToHex(int A)
  { 
return (Integer.toHexString(A));
  } 
privateintrotateLeft(int A, int B)
  {
return (A << (B & (W-1))) | (A >>> (W-(B & (W-1))));
  }
privateintrotateRight(int A, int B)
  {
return (A>>>(B&(W-1)))|(A<<(W-(B&(W-1))));
  }

  /*Set the key for this block cipher. */

public void setKey(byte[] key,int[] S)throws ArrayIndexOutOfBoundsException
  {
S[0] = Pw;
for (int i=1;i <t;i++)S[i]=S[i-1]+Qw;
int L[]=new int[4];
   L[c-1]=0;
for(int i = b-1; i >= 0; i--)
   {
L[i/u]=(L[i/u]<<8)+key[i];
   }
int i=0,j=0,A=0,B=0;
intupto=(3*((t>c)?t:c));
for (int k = 0; k <upto; k++)
   {
    S[i] = rotateLeft(S[i] + A + B, 3);
    A = S[i];
    L[j] = rotateLeft(L[j] + A + B, A + B);
    B = L[j];
    i = (i + 1) % t;
    j = (j + 1) % c;
  }
}


public void  decrypt(int[] S)
 {
int A,B;
int i;
  A=CT[0];B=CT[1];
for(i=rounds;i>0;i--)
  {
   B=rotateRight(B-S[2*i+1],A)^A;
   A=rotateRight(A-S[2*i],B)^B;
  }
PT[0]=A-S[0];
PT[1]=B-S[1];

 }
public static String  dec(String str1,String str2) throws IOException
 {
int[] S = new int[26];
byte key[] = str1.getBytes();
  RC5d rc5 = new RC5d();
try{
rc5.setKey(key,S);
}catch(ArrayIndexOutOfBoundsException                          
{System.out.println("\nWrong key");System.exit(0);} 
 // FileInputStreamfl=new FileInputStream("test.txt");
System.out.println();
byte[]obytes=new byte[4];
StringBuilder res = new StringBuilder();

  String s = null;
 // String abc[];
try {
	
	String[] re= str2.split("8745632");
	 // System.out.println("Ramanaaaaaa le++++++++++++++"+re.length);
for(int j=0;j<=re.length;j++){
	try{
String	abc[]= re[j].split("2345678");

rc5.CT[0]=Integer.parseInt(abc[0]);
rc5.CT[1]=Integer.parseInt(abc[1]);
}catch(NumberFormatException e){
	e.printStackTrace();
}catch(ArrayIndexOutOfBoundsException e){
	e.printStackTrace();
}
rc5.decrypt(S);

	System.out.println("++++"+rc5.intToAscii(0));
	System.out.println("------"+rc5.intToAscii(1));
	s=( rc5.intToAscii(0)+""+rc5.intToAscii(1)).toString();
	res.append(s);
}
} catch (Exception e) {
	// TODO Auto-generated catch block
	
	e.printStackTrace();
	String error="Entered key is wrong.Message can't be displayed";
	return error;
}


 // }


//  fl.close();

return  res.toString();

}

privateStringBuilderintToAscii(int a)throws Exception
  {
StringBuilder output = new StringBuilder();
if(PT[a]==0){ output.append("");return output;}
   String hex = intToHex(PT[a]);
for (int i = 0; i <hex.length(); i+=2) {

   String str = hex.substring(i, i+2);
output.append((char)Integer.parseInt(str,16));
    }
return output;

  }

private void writeToFile(FileOutputStreamfout)throws IOException

   {
byte[]bytes = new byte[4];
bytes=intToBytes(CT[0],4);
fout.write(bytes);
bytes=intToBytes(CT[1],4);
fout.write(bytes);
   }
public static byte[] intToBytes(int x, int n) {
byte[] bytes = new byte[n];
for (int i = 0; i < n; i++, x >>>= 8)
bytes[i] = (byte) (x & 0xFF);
return bytes;
  }
public static intbytesToInt(byte[] x) {
int value = 0;
for(int i = 0; i <x.length; i++)
value += ((long) x[i] & 0xffL) << (8 * i);
return value;
   }  
}

