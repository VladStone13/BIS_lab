public class Main {
    public static void main(String args[]) {
        BigInt firstNumber = new BigInt("155555555555518");
        BigInt secondNumber = new BigInt("4782087");

        firstNumber.add(secondNumber).print();
        firstNumber.multiply(secondNumber).print();
        secondNumber.square().print();
        firstNumber.divineRemainder(secondNumber).print();
    }
}
