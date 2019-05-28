import java.util.*;
import java.math.*;
import java.io.*;

public class RSA_Destroyer_Kit {
	//45238049
	private static String[] alphabet =  {"::FATAL ERROR::","0","1","2","3","4","5","6","7","8","9"," ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",",",":","."};
	private static String[] message = {"375611", "125336", "421644", "325129", "495171", "142428", "458171", "269786", "326504", "164977", "236038", "206659"};
	
	public static void main(String[] args) {
		//for later
		BigInteger decodingKey =  new BigInteger("13147");
		BigInteger modulus =  new BigInteger("520229");
		BigInteger[] messageInts =  new BigInteger[message.length];
		for (int i = 0; i<messageInts.length; i++) {
			messageInts[i] = new BigInteger(message[i]);
		}		
		solve(messageInts, decodingKey, modulus);
	}

	private static void solve(BigInteger[] nums, BigInteger decodingKey, BigInteger modulus) {
		//ArrayList<String> out = new ArrayList<String>();
		String[] out = new String[3];
		out[0] = "";
		out[1] = "";
		out[2] = "";
		for (BigInteger num: nums) {
			String[] tmp = base41ToAscii(num.modPow(decodingKey, modulus));
			System.out.println(num.modPow(decodingKey, modulus));
			out[0] += tmp[0];
			out[1] += tmp[1];
			out[2] += tmp[2];
		}
		System.out.println(out[0]);
		System.out.println(out[1]);
		System.out.println(out[2]);
	}

	private static String[] base41ToAscii(BigInteger num) {
		BigInteger cNum = new BigInteger(num.toString());
		BigInteger base = new BigInteger("41");
		BigInteger pow = FindHighestPower(cNum, base);
		//BigInteger pow = new BigInteger("2");
		BigInteger[] sols = new BigInteger[pow.intValue()+1];
		
		for (int i = 0; i<sols.length; i++) {
			sols[i] = cNum.divide(base.pow(pow.intValue()));
			cNum = cNum.mod(base.pow(pow.intValue()));
			
			pow = pow.subtract(new BigInteger("1"));
		}
		
		String out1 = "";
		String out2 = "";
		for (int i = 0; i<sols.length; i++) {
			out1 += alphabet[sols[i].intValue()];
			out2 += alphabet[sols[sols.length-i-1].intValue()];
		}
		
		String[] result = {Arrays.toString(sols),out1,out2};
		return result;
	}

	private static BigInteger FindHighestPower(BigInteger num, BigInteger base) {
		Integer pow = 0;
		while (!num.divide(base.pow(pow)).equals(new BigInteger("0"))) {
			pow++;
		}
		--pow;
		return new BigInteger(pow.toString());
	}

}
