import static org.junit.jupiter.api.Assertions.*;

class BigIntTest {
    private BigInt firstNumber;
    private BigInt secondNumber;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        firstNumber = new BigInt("145327935334863346325486321852452456543456456546");
        secondNumber = new BigInt("897458946518486184897428974374875732587578584");
    }

    @org.junit.jupiter.api.Test
    void add() {
        BigInt result = firstNumber.add(secondNumber);
        assertEquals(result.getNumber(), "146225394281381832510383750826827332276044035130");
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        BigInt result = firstNumber.multiply(secondNumber);
        assertEquals(result.getNumber(), "130425855745333142598097485900475249673260124327829482328127641423111064104933620874956210864");
    }

    @org.junit.jupiter.api.Test
    void square() {
        BigInt result = firstNumber.square();
        assertEquals(result.getNumber(), "21120208788694222369478990763427174121707170705655941450753553828681333050734874113534386250116");
    }

    @org.junit.jupiter.api.Test
    void divineRemainder() {
        BigInt result = firstNumber.divineRemainder(secondNumber);
        assertEquals(result.getNumber(), "837044945387070557000256978097463596856304522");
    }
}