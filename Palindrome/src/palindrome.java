
public class palindrome {
	public static boolean isPalindrome(String s) {
		s = s.toLowerCase();
		s = s.replaceAll(" ", "");
		for (int i = 0; i<s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
			
		}return true;
	}
	public static void main(String[] args) {
		System.out.println(isPalindrome("Hannah"));
		System.out.println(isPalindrome("Hannah hannah"));
		System.out.println(isPalindrome("cat"));
	}

}
