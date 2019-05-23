package pust.random_person_generator;

import org.junit.Assert;
import org.junit.Test;
import pust.model.utility.random_generator.person.RandomPersonalNumber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class RandomPersonalNumberTest {

    private RandomPersonalNumber personalNumber = new RandomPersonalNumber();

    @Test
    public void calculateControlNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("calculateControlNumber", int.class, int.class, int.class, int.class);
        method.setAccessible(true);

        int expected = 4;

        int actual = (int) method.invoke(personalNumber, 1983, 06, 19, 353);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void devSplitArrayWhileValueHasTensDevelopMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("splitSsnToArray", int.class);
        method.setAccessible(true);

        int[] ssn1 = {16, 3, 0, 6, 2, 9, 6, 5, 6};
        int[] ssn = {18, 1, 0, 5, 0, 8, 6, 9, 14};

        ArrayList<Integer> splittedArray = new ArrayList<>();
        for (int i = 0; i < ssn.length; i++) {
            int[] arr = (int[]) method.invoke(personalNumber, ssn[i]);
            for (int j = 0; j < arr.length; j++) {
                splittedArray.add(arr[j]);
            }
        }

        int temp = 0;
        for (int i = 0; i < splittedArray.size(); i++) {
            temp += splittedArray.get(i);
        }

        ssn = (int[]) method.invoke(personalNumber, temp);
        temp = ssn[ssn.length - 1];
    }

    @Test
    public void devLuhnAlgorithmMultiplicationPart() {
        int[] ssn1 = {8, 3, 0, 6, 1, 9, 3, 5, 3};
        int[] ssn = {9, 1, 0, 5, 0, 8, 3, 9, 7};

        int counter = 2;
        for (int i = 0; i < ssn.length; i++) {
            ssn[i] = ssn[i] * counter;
            if (counter == 2) {
                counter = 1;
            } else {
                counter = 2;
            }
        }
    }

    @Test
    public void calculateTheLastDigitInSsn() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("calculateControlNumber", int.class, int.class, int.class, int.class);
        method.setAccessible(true);

        int expected = 6;

        int actual = (int) method.invoke(personalNumber, 1983, 06, 19, 353);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void splitIntegerToArray() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("splitSsnToArray", int.class);
        method.setAccessible(true);

        int[] expected = {8, 3, 0, 6, 1, 9, 3, 5, 3};

        int[] actual = (int[]) method.invoke(personalNumber, 830619353);

        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }

    }

    @Test
    public void addLeadingZero() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("checkLeadingZero", int.class);
        method.setAccessible(true);

        String expected = "01";

        String actual = (String) method.invoke(personalNumber, 1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isLeapYearEveryFourthYear() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("isLeapYear", int.class);
        method.setAccessible(true);

        int testValue = 2016;

        boolean isLeapYear = (boolean) method.invoke(personalNumber, testValue);

        Assert.assertTrue(isLeapYear);
    }

    @Test
    public void isLeapYearEveryHundredYear() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("isLeapYear", int.class);
        method.setAccessible(true);

        int testValue = 1700;

        boolean isLeapYear = (boolean) method.invoke(personalNumber, testValue);

        Assert.assertFalse(isLeapYear);
    }

    @Test
    public void isLeapYearEveryFourHundredYear() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("isLeapYear", int.class);
        method.setAccessible(true);

        int testValue = 1600;

        boolean isLeapYear = (boolean) method.invoke(personalNumber, testValue);

        Assert.assertTrue(isLeapYear);
    }


    @Test
    public void trimSsnToArrayReturnValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = RandomPersonalNumber.class.getDeclaredMethod("parseSsnToArray", int.class, int.class, int.class, int.class);
        method.setAccessible(true);

        int[] expected = {9, 4, 0, 2, 1, 8, 3, 5, 4};

        int[] actual = (int[]) method.invoke(personalNumber, 1994, 02, 18, 354);

        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void randomYear() {
        RandomPersonalNumber personalNumber;
        boolean yearIsCorrect = false;

        int actualYear = -1;

        for (int i = 0; i < 1000000; i++) {
            personalNumber = new RandomPersonalNumber();
            actualYear = personalNumber.getYear();
            if (actualYear >= 1930 && actualYear <= 2011) {
                yearIsCorrect = true;
            } else {
                yearIsCorrect = false;
            }
            Assert.assertTrue(yearIsCorrect);
        }
    }

    @Test
    public void randomMonth() {
        RandomPersonalNumber personalNumber;
        boolean monthIsCorrect = false;
        int actualMonth = -1;

        for (int i = 0; i < 1000000; i++) {
            personalNumber = new RandomPersonalNumber();
            actualMonth = personalNumber.getMonth();
            if (actualMonth >= 1 && actualMonth <= 12) {
                monthIsCorrect = true;
            } else {
                monthIsCorrect = false;
            }
            Assert.assertTrue(monthIsCorrect);
        }
    }

    @Test
    public void randomDay() {
        RandomPersonalNumber personalNumber;
        boolean dayIsCorrect = false;
        int actualDay = -1;

        for (int i = 0; i < 1000000; i++) {
            personalNumber = new RandomPersonalNumber();
            actualDay = personalNumber.getMonth();
            if (actualDay >= 1 && actualDay <= 31) {
                dayIsCorrect = true;
            } else {
                dayIsCorrect = false;
            }
            Assert.assertTrue(dayIsCorrect);
        }
    }
}