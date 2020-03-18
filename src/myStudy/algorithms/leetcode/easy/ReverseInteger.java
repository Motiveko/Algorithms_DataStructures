package myStudy.algorithms.leetcode.easy;

//숫자뒤집기
public class ReverseInteger {

	// solution 1, String으로 밀어넣고 마지막에 parseInt!
	public static int reverseInt(int num) {
		String str = "";
		for (int i = num % 10; num != 0; i = num % 10) {
			str += i;
			num /= 10;
		}
		return Integer.parseInt(str);
	}

	// solution 2 , 진짜 숫자계산으로 처리
	public static int reverseInt2(int num) {
		int ans = 0;
		for (; num != 0; num /= 10) {

			ans = ans * 10 + num % 10;

		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(reverseInt(546811));
		System.out.println(reverseInt2(546811));
	}

}
