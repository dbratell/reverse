package sarasas;

import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author Daniel Bratell
 */
public class Reverse
{
    private int[] mNumbers;
    private int mReverseCount = 0;

    public Reverse(int length)
    {
        mNumbers = new int[length];
        ArrayList freeSlots = new ArrayList(mNumbers.length);
        for (int i = 0; i < mNumbers.length; i++)
        {
            freeSlots.add(new Integer(i));
        }

        Random rand = new Random();
        for (int i = 1; i <= mNumbers.length; i++)
        {
            int slot = rand.nextInt(freeSlots.size());
            Integer pos = (Integer)freeSlots.get(slot);
            mNumbers[pos.intValue()] = i;
            freeSlots.remove(slot);
        }
    }

    private String getNumbersAsString()
    {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < mNumbers.length-1; i++)
        {
            string.append(mNumbers[i]);
            string.append(", ");
        }
        string.append(mNumbers[mNumbers.length-1]);
        return string.toString();
    }

    public static void main(String[] args) throws IOException
    {
        int number = 9; // default;
        if (args.length == 1)
        {
            try
            {
                int argNumber = Integer.parseInt(args[0]);
                if (argNumber > 1)
                {
                    number = argNumber;
                }
            }
            catch (NumberFormatException e)
            {
                System.err.println("Skipping input "+args[0]);
            }
        }
        new Reverse(number).start();
    }

    private void start() throws IOException
    {
        while (!correctOrder())
        {
            System.out.println(getNumbersAsString());
            System.out.print("Hur många från vänster vill du vända? ");
            int number = getNumber();

            reverseNumbers(number);
        }

        System.out.println("Grattis! Det tog "+mReverseCount+" vändningar.");
    }

    private void reverseNumbers(int number)
    {
        for (int i = 0; i < number / 2; i++)
        {
            int oppositeI = number - i -1;
            int tempInt = mNumbers[i];
            mNumbers[i] = mNumbers[oppositeI];
            mNumbers[oppositeI] = tempInt;
        }

        mReverseCount++;
    }

    private int getNumber() throws IOException
    {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String string = in.readLine();
            try
            {
                int number = Integer.parseInt(string);
                if (number > 1 && number <= mNumbers.length)
                {
                   return number;
                }

                System.out.println("Det var inte ett giltigt intervall. " +
                                   "Försök igen.");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Det var inte ett nummer. Försök igen.");
            }
        }

    }

    private boolean correctOrder()
    {
        for (int i = 0; i < mNumbers.length; i++)
        {
            if (mNumbers[i] != i+1)
            {
                return false;
            }
        }
        return true;
    }
}
