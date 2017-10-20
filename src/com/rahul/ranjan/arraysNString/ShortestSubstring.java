package com.rahul.ranjan.arraysNString;


// Read only region start
class ShortestSubstring
{

	public String GetSubString(String input1,String input2){
		// Read only region end

        return find(input1, input2);
    }
  private static boolean containsPatternChar(int[] sCount, int[] pCount) {
        for(int i=0;i<256;i++) {
            if(pCount[i]>sCount[i])
                return false;
        }
        return true;
    }
  public static String find(String s, String p) {
        if (p.length() > s.length())
            return null;
        int[] pCount = new int[256];
        int[] sCount = new int[256];
        // Time: O(p.lenght)
        for(int i=0;i<p.length();i++) {
            pCount[(int)(p.charAt(i))]++;
            sCount[(int)(s.charAt(i))]++;
        }
        int i = 0, j = p.length(), min = Integer.MAX_VALUE;
        String res = null;
        // Time: O(s.lenght)
        while (j < s.length()) {
            if (containsPatternChar(sCount, pCount)) {
                if ((j - i) < min) {
                    min = j - i;
                    res = s.substring(i, j);
                    // This is the smallest possible substring.
                    if(min==p.length())
                        break;
                    // Reduce the window size.
                    sCount[(int)(s.charAt(i))]--;
                    i++;
                }
            } else {
                sCount[(int)(s.charAt(j))]++;
                // Increase the window size.
                j++;
            }
        }
        System.out.println(res);
        return res;
    }
}