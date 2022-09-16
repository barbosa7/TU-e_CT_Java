
/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO: Documentation
 * 
 * END TODO
 * 
 * @author <NAME STUDENT 1>
 * @ID <ID STUDENT 1>
 * @author <NAME STUDENT 2>
 * @ID <ID STUDENT 2>
 * 
 */
import java.util.*;

class KingsPalindromeList {

    public static long getParts(long number, int half, int len)
    {
        
        if(half == 1) // 1 -> gets first half
        number /= Math.pow(10, ((len +1)) / 2);
        else if(half == 2) // 2 -> gets second half
        number %= Math.pow(10, ((len -1)) / 2);
        else if(half == 0) // 0 -> gets middle value
        {
            number /= Math.pow(10, ((len -1)) / 2);
            number %= 10;
        }
        return number;
    }
    public static long nbrRev(long number)
    {
        //Reverses a number
        long revNumber = 0;
        while(number > 0)
        {
            revNumber = revNumber * 10 + (number % 10);
            number /= 10;
        }
        return revNumber;
    }
    public static int getLen(long number)
    {
        int len = 1;
        while(number > 10)
        {
            len ++;
            number /= 10;
        }
        return len;
    }
    public static boolean magicSetCompare(long nbr1, long nbr2)
    {
        int nbr1Len = getLen(nbr1);
        int nbr2Len = getLen(nbr2);
        if(nbr2Len == 1)
            return true;
        long nbr1FirstHalf = getParts(nbr1, 1, nbr1Len);
       // System.out.println("The first half of nbr1 is " + nbr1FirstHalf);
        long nbr2FirstHalf = getParts(nbr2, 1, nbr2Len);
       // System.out.println("The first half of nbr2 is " + nbr2FirstHalf);
        long divisor = (long)Math.pow(10, (nbr2Len - 1) / 2);
       // System.out.println("The divisior is " + divisor);
        if (nbr1FirstHalf % divisor == nbr2FirstHalf)
            return true;
        else
            return false;
    }
    public static void checkMagicSet(long[] nbr, int n)
    {
        int nbr1Len, nbr2Len;
        long nbr1Mid, nbr2Mid;
        Arrays.sort(nbr);
        for(int i = 0; i < n; i++) {
            nbr1Len = getLen(nbr[i]);
            nbr1Mid = getParts(nbr[i], 0, nbr1Len);
            System.out.println("Element" + i + " = " + nbr[i]);
            for(int j = i; i < n; j++)
            {
                nbr2Len = getLen(nbr[j]);
                nbr2Mid = getParts(nbr[j], 0, nbr2Len);
                //they only enter here if middle is equal;
                //and only if nbr1Len > nbr2Len;
                if(nbr1Mid == nbr2Mid && nbr1Len > nbr2Len)
                    if(magicSetCompare(nbr[i], nbr[j]))
                    {
                        //store the magic family;
                    }
            }
        }
    }
    public static long getCorrectPalindrome(long value)
    {
        long number = value;
        int len;
        long firstHalf;
        long secondHalf;
        long firstHalfRev;
        long middle;
 
        len = getLen(number);
        firstHalf = getParts(number, 1, len);
        secondHalf = getParts(number, 2, len);
        firstHalfRev = nbrRev(firstHalf);
        middle = getParts(number, 0, len);
       
        //Get the next Palindrome
        if(firstHalfRev == secondHalf)
           return number;
        else if(firstHalfRev > secondHalf) {
            //just copy the first to second but reversed.
            number -= secondHalf;
            number += firstHalfRev;
        } else {
            number += Math.pow(10, ((len -1) / 2)); // add 1 to middle
            firstHalf = getParts(number, 1, len); // get first half
            middle = getParts(number, 0, len); // get new middle
            number = firstHalf * (long)Math.pow(10, ((len+1) / 2)); // get the first half int the right slots
            number += middle * (long)Math.pow(10, ((len-1) / 2)); // get middle in the middle slot
            number += nbrRev(firstHalf); // copy the first half but reversed to the second half
        }
        return number;
    }
    public static void KingsPalindromeSolver()
    {
        Scanner input = new Scanner(System.in);
        int task = Integer.parseInt(input.nextLine());
        int n = Integer.parseInt(input.nextLine()); // get number of ints
        long[] peterList = new long[n];

        // for each int get the correct palindrome
        for(int i = 0; i < n; i++)
        {
            long value = input.nextLong();
            peterList[i] = getCorrectPalindrome(value);
            //System.out.println(peterList[i]);
        }
        checkMagicSet(peterList, n);
        // find correct list
    }

    public static void main(String[] args) {
        KingsPalindromeSolver();
    }
}