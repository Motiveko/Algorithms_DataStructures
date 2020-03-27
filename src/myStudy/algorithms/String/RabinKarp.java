package myStudy.algorithms.String;

import java.math.BigInteger;
/*
 * 참고 :: https://blog.naver.com/dinky_apple/221850206355
 * Rolling Hash :: shttps://blog.naver.com/babobigi/220911965116 
 * Rabin-Karp는 어떤 문자열S에서 패턴 P(subString)을 찾기 위한 함수다.
 * Naive한 함수는 S를 P의 길이만큼 각각 잘라서 전부 검사하면 O(SP)가 나오는데
 * Rabin-Karp는 P를 어떤 int k로 Hashing하고, S도 전부  P길이만큼 잘라서 정수로 Hashing한 
 * 뒤 여기서 k를 찾아내는 방식으로 작동한다. 따라서 Complexity는 O(S)
 * 
 * 알고리즘의 효율의 핵심은 해싱을 얼마나 잘 하느냐에 달려있다.(최소의 해시충돌)
 */
import java.util.*;

public class RabinKarp {
	
	private static final long ALPHABET_BASE = 95 + 1;
	private static final long[] ALPHABET = new long[127];
	private static final BigInteger BIG_ALPHA = new BigInteger(String.valueOf(ALPHABET_BASE));
	
	private static final long[] MODS = {10_000_019, 10_000_079, 10_000_103};
	private static final int N_HASHES = MODS.length;
	private static final BigInteger[] BIG_MODS = new BigInteger[N_HASHES];
	private static final long[] MOD_INVERSES = new long[N_HASHES];

	public static List<Integer> rabinKarp(String text, String pattern){
		
		
		return null;
	}
	
	
	public static List<Integer> rabinKarpBackwards(String text, String pattren){
		
		return null;
	}
	
	private static long addRight(long rollingHash, char lastValue, int modIndex) {
	    
		rollingHash = (rollingHash * ALPHABET_BASE + ALPHABET[lastValue]) % MODS[modIndex];
	    return (rollingHash + MODS[modIndex]) % MODS[modIndex];

	}
	
	private static long removeRight(long rollingHash, char lastValue, int modIndex) {
		
		return 0;
	}
	
	private static long removeLeft(
			long rollingHash, long alphabetBasePower, char firstValue, int modIndex) {
		
		return 0;
	}
	
	public static long[] computeHash(String str) {
		
		long[] rollingHashes = new long[N_HASHES];
		for( int k = 0; k < N_HASHES; k++) {
			for(int i=0; i < str.length(); i++)
				rollingHashes[k] = addRight(rollingHashes[k], str.charAt(i), k);
		}
		return rollingHashes;
	}
	
	
}
