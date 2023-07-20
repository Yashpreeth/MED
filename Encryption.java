packagecom.example.med;

importjava.lang.*;
import java.io.*;
importjava.util.*;



public class RC5ee
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

public RC5ee()
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

public void setMyKey(byte[] key,int[] S)
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

public String encrypt(byte[] plainText,intst,int[] S) { 
int A=0,B=0;
try{
for(int i=0+st;i<4+st;i++)A=(A<<8)+plainText[i+st];
for(int i=4+st;i<8+st;i++)B=(B<<8)+plainText[i+st];
}catch(Exception e){}
  A = A + S[0];
  B = B + S[1];
for (int i = 1; i <= rounds; i++) {
   A = rotateLeft(A ^ B, B) + S[2 * i];
   B = rotateLeft(B ^ A,A) + S[2 * i + 1];
  }
CT[0]=A;
CT[1]=B;
System.out.println("fffffffffffffffffffffffffffffffffffffff");
return CT[0]+"2345678"+CT[1];

 }

public static String enc(String str1,String str2) throws IOException
{
	String str = null;
byte key[] = str1.getBytes();
byte [] plainText=str2.getBytes();
int[] S = new int[26];
  RC5ee rc5 = new RC5ee();
rc5.setMyKey(key,S);
 // StringBuildersb = null;
StringBuildersb= new StringBuilder();
System.out.println(str2.length());
  //FileOutputStreamfo=new FileOutputStream("test.txt");
for(int i=0,k=0;i<str2.length();i+=8,k+=4){
 // rc5.encrypt(plainText,k,S);
	
	sb.append( rc5.encrypt(plainText, k, S)+"8745632");
	System.out.println("rotationnnnnnnnn");
	
  // str = rc5.encrypt(plainText, k, S);
  //rc5.writeToFile(fo);
  }
 // fo.close();
System.out.println("((((((((((((((((((("+sb.toString());
returnsb.toString();
}
privateStringBuilderintToAscii(int a){
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
