import java.util.Arrays;

public class Test10 {

	public static void main(String[] args) {
		
//		int a = 7;
//		String b = "123";
//		System.out.println((float)a);
//		
//		
//		System.out.println( Integer.toString(a) + 8	);
//		
//		System.out.println( Integer.parseInt(b) + a);
//		
		
//		String s = "Hello World!!";
//		//문자열.replace(찾을값,바꿀값) : 다바꿈
//		System.out.println(s.replace("l", "k"));
//		System.out.println(s.replaceAll("l", "k"));
//		//문자열.replaceFirst(찾을값,바꿀값) : 첫번빼만 바꿈
//		System.out.println(s.replaceFirst("l", "k"));
//		
//		System.out.println(s.indexOf("l"));
//		//indexof(찾을값,시작위치) : 시작위치 값은 위치를 알려준다
//		//indexOf : 오름차순, lastIndexOf : 내림차순
//		System.out.println(s.indexOf("l", 4));
//		System.out.println(s.charAt(1));
//		
//		//substring( index)  index이상
//		//substring(index1,2) index 이상, 미만 값
//		System.out.println(s.substring(2));
//		System.out.println(s.substring(2,4));
//		
//		//문자열.length() : 문자열의 길이
//		System.out.println(s.length());
//		
//		//대문자화, 소문자화
//		System.out.println(s.toUpperCase());
//		System.out.println(s.toLowerCase());
//		
//		String s2 = "가나다,라마바,사아자";
//		// 문자열.split(구분자) : 구분로 문자열을 자르고 배을로 만듬
//		String[] arr = s2.split(",");
//		
//		System.out.println(Arrays.toString(arr));
//		
//		
//		

		
		
//		//개미수열
		int[]arr1 = {1} ;
		int[]arr2;
		
		int offset;
		int count;
		int index;
		
		System.out.println("LEVEL 01 : 1");
		for(int i=0; i<9; i++) {
			//레벨출력
			int level = i+2;
			if(level<10) System.out.print("LEVEL 0" + (i+2) + " : ");
			else System.out.print("LEVEL " + (i+2) + " : ");
			
			//초기화
			offset=arr1[0];
			arr2 = new int[arr1.length*2];
			arr2[0] = offset;
			count = 0;
			index=1;
			//계산
			for(int j=0; j<arr1.length && arr1[j]!=0; j++) {
				if(arr1[j] == offset) {
					count++;
				}
				else {
					arr2[index++] = count;
					offset=arr1[j];
					arr2[index++] = offset;
					count =1;
				}
			}
			arr2[index] = count;
			arr1 = new int[arr2.length];

			//arr2 출력 및 arr2->arr1
			for(int j=0;  j<arr2.length && arr2[j]!=0 ; j++) {
				System.out.print(arr2[j]);
				arr1[j] = arr2[j];
			}
			System.out.println();
		}
	}
}
