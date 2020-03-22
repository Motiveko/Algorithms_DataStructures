package myStudy.algorithms.leetcode.medium;

//Palindrome :: 앞에서부터 읽어도, 뒤에서부터 읽어도 똑같은 문자열
//O(n)인 ManachersAlgorithm은 따로 구
public class LongestPalindromicSubstring {

	//MYCODE , 길n인 문자열부터 하나씩 검사해서 한번이라도 Palindrome이 나오면 멈춘다
	//O(n^2) , 별루
	public static String longestPalindrome1(String s) {
		
		int n = s.length();
		String subString;
		int start, end;
		boolean isPalindrome;
		//i는 길이
		for(int i=n; i>1; i--) {
			for(int j=0; j+i<=n ; j++) {
				isPalindrome = true;
				subString = s.substring(j,j+i);
//				System.out.println(j + ", " + i);
				start = 0; end = subString.length() -1;
				
				while( start < end) {
					if( subString.charAt(start++) != subString.charAt(end--)) {
						isPalindrome = false;
						break;
					}
				}
				if(isPalindrome) return subString;
			}
		}
		
		
		return s.substring(0,1);
	}

/*		
	//Github 1, 노가다인건 마찬가지.

	//i를 center로 해서 Palindrome을 찾는다. longest를 찾을때도 무조건 끝까지 검사해야하므로 좋진않다.
	//O(n^2) , 별루

    public static int st, end;

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
        	//Palindrome이 홀수 , 짝수일때 각각 구한
            helper(chars, i, i);
            helper(chars, i, i + 1);
        }
        return s.substring(st, end + 1);
    }

    private static void helper(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            --l;
            ++r;
        }
        if (end - st < r - l - 2) {
            st = l + 1;
            end = r - 1;
        }
    }	
 */  
	
	// Dynamic programing , i와 j는 각각 end,start지점인데
	// DP[j][i] 는 j번째와 i번째 문자가 같고 DP[j+1][i-1]도 true일때만 true이다
	// 간단한 dynamic programing 이지만 익숙치 않으므로 열씨미 익히자.
	//O(n^2)으로 별루다.
	public static String longestPalindrome(String s) {
		
		int st = 0, end = 0;
		int len = s.length();
		char[] chars = s.toCharArray();
		boolean[][] DP = new boolean[len][len];
		
		for(int i=0; i<len; i++) {
			DP[i][i] = true;
			
			for(int j=0; j<i; j++) {
				
				if( j+1 == i) {
					DP[j][i] = chars[j] == chars[i];
				} else {
					DP[j][i] = DP[j+1][i-1] && chars[j]==chars[i];
				}
				
				if(DP[j][i] && end-st < i-j) {
					end = i;
					st = j;
				}
			}
		}
		return s.substring(st,end+1);
		
	}
	public static void main(String[] args) {
		
		System.out.println(longestPalindrome("adfadaabaafadf"));
	}
}
