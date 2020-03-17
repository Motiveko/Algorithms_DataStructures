package myStudy.algorithms.algorithms;

//MEDIUM,!
/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
Example 1:
Given s = "internationalization", abbr = "i12iz4n":
Return true.
Example 2:
Given s = "apple", abbr = "a2e":
Return false.
*/

/*
abbr uses int to replace letters.
walk through abbr, if non-int letter from abbr all matches original word, and travese completes, it's a true.
*/
public class ValidWordAbbreviation {

	public static boolean isValidWordAbbreviation(String word, String abbr) {
		
		int wordLen = word.length();
		int abbrLen = abbr.length();
		int count = 0;
		
		for(int i=0; i < abbrLen ;  ) {
			
			char ch = abbr.charAt(i);

			if( i+count < wordLen && ch == word.charAt(i + count)) {
				i++;
				continue;
			} else if(!isInt(ch)) return false;
			else {
				String str = "";
				while( i<abbrLen && isInt(ch) ) {
					str += ch;
					i++;
					ch = abbr.charAt(i);
					//보정
					count--;
				}
				// 이것은 잊어먹으면 안되는 것입니다..
				count += Integer.parseInt(str);
			}
		}
		return true;
	}
	
	private static boolean isInt(char ch) {
		return (ch >=49) && (ch <= 59);
	}
	
	// parseInt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static void main(String[] args) {
		
		System.out.println(isValidWordAbbreviation("internationalization", "i12iz4n"));
		
		System.out.println(isValidWordAbbreviation("apple", "a4e"));
	}
}
