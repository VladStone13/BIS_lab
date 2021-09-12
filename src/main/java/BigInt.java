import java.util.ArrayList;

public class BigInt {
    private String number;
    private boolean isPositive;

    public BigInt() {}

    public BigInt(String number) {
        if (number.charAt(0) == '-') {
            isPositive = false;
            number = number.substring(1);
        }
        else {
            isPositive = true;
        }
        this.number = number;
    }

    public BigInt(BigInt other) {
        number = other.number;
        isPositive = other.isPositive;
    }

    private int compare(String firstNumber, String secondNumber) {
        if (firstNumber.length() != secondNumber.length()) {
            return firstNumber.length() > secondNumber.length() ? 1 : -1;
        }

        for (int i = 0; i < firstNumber.length(); ++i) {
            int firstDigit = Character.getNumericValue(firstNumber.charAt(i));
            int secondDigit = Character.getNumericValue(secondNumber.charAt(i));

            if (firstDigit != secondDigit) {
                return firstDigit > secondDigit ? 1 : -1;
            }
        }

        return 0;
    }

    private String beautify(String number) {
        if (number.length() == 1) {
            return number;
        }

        while (number.charAt(0) == '0') {
           number = number.substring(1);
           if (number.length() == 1) {
               return number;
           }
        }

        return number;
    }

    public BigInt add(BigInt other) {
        int thisSize = number.length();
        int otherSize = other.getNumber().length();

        boolean isOneSign = isPositive == other.isPositive();

        int compareString = compare(number, other.getNumber());
        boolean sign = isPositive;
        String biggerNumber = number;
        String smallerNumber = other.getNumber();

        if (compareString == -1) {
            biggerNumber = other.getNumber();
            smallerNumber = number;
            sign = other.isPositive();
        }

        String result = "";
        int nextLevel = 0;

        for (int i = 0; i < smallerNumber.length() ; ++i) {
            int firstNumber = Character.getNumericValue(smallerNumber.charAt(smallerNumber.length() - i - 1));
            int secondNumber = Character.getNumericValue(biggerNumber.charAt(biggerNumber.length() - i - 1));

            if (isOneSign) {
                if (firstNumber + secondNumber + nextLevel >= 10) {
                    result += (firstNumber + secondNumber - 10 + nextLevel);
                    nextLevel = 1;
                }
                else {
                    result += (firstNumber+secondNumber + nextLevel);
                    nextLevel = 0;
                }
            }

            else {
                if (secondNumber - firstNumber + nextLevel < 0) {
                    result += (secondNumber - firstNumber + nextLevel + 10);
                    nextLevel = -1;
                }
                else {
                    result += (secondNumber - firstNumber + nextLevel);
                    nextLevel = 0;
                }
            }
        }

        for (int i = biggerNumber.length()-smallerNumber.length() - 1; i >= 0; --i) {
            int firstNumber = Character.getNumericValue(biggerNumber.charAt(i));

            if (isOneSign) {
                if (firstNumber + nextLevel >= 10) {
                    result += (firstNumber + nextLevel - 10);
                    nextLevel = 1;
                }
                else {
                    result += (firstNumber + nextLevel);
                    nextLevel = 0;
                }
            }
            else {
                if (firstNumber + nextLevel < 0) {
                    result += (firstNumber + nextLevel + 10);
                    nextLevel = -1;
                }
                else {
                    result += (firstNumber + nextLevel);
                    nextLevel = 0;
                }
            }
        }

        result = new StringBuilder(result).reverse().toString();
        result = beautify(result);

        if (!sign) {
            return new BigInt("-" + result);
        }
        return new BigInt(result);
    }

    private BigInt multiplyByDigit(BigInt number, int digit, int countZero) {
        String result = "";

        for (int i = 0; i < countZero; ++i) {
            result += "0";
        }

        int nextLevel = 0;

        for (int i = number.getNumber().length()-1; i >= 0; --i) {
            int digitInNumber = Character.getNumericValue(number.getNumber().charAt(i));
            result += ((digitInNumber * digit) + nextLevel) % 10;
            nextLevel = ((digitInNumber * digit) + nextLevel) / 10;
        }

        if (nextLevel != 0) {
            result += nextLevel;
        }

        return new BigInt(new StringBuilder(result).reverse().toString());
    }

    public BigInt multiply(BigInt other) {
        BigInt result = new BigInt("0");
        for (int i = other.getNumber().length()-1; i >= 0; --i) {
            result = result.add(multiplyByDigit(this, Character.getNumericValue(other.getNumber().charAt(i)), other.getNumber().length()-i-1));
        }

        if (isPositive != other.isPositive) {
            result.setPositive(false);
        }
        return result;
    }

    public BigInt square() {
        return this.multiply(this);
    }

    public BigInt divineRemainder(BigInt other) {
        BigInt bigger = new BigInt(this);
        BigInt smaller = new BigInt(other);
        smaller.setPositive(false);


        while (compare(bigger.getNumber(), smaller.getNumber()) == 1) {
            bigger = bigger.add(smaller);
        }

        return bigger;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void print() {
        if (isPositive) {
            System.out.println(number);
        }
        else  {
            System.out.println("-" + number);
        }
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }
}
