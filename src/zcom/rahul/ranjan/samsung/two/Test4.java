package zcom.rahul.ranjan.samsung.two;

public class Test4 {
public static void main(String[] args) {
	String str="aeiaaioooaauuaeiou";
	String str2="aeiaaioooaa";
	String regex="a.e.io+u+";
	System.out.println(str.split(regex)[0]);
	
}
}
