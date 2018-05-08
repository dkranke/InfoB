public class StackTest {
    public static void main(String[] args) {
        //Creating stack and copy
        StringStack original = new StringStack();
        original.push("0");
        original.push("1");
        original.push("2");
        original.push("3");
        original.push("4");
        StringStack copy = new StringStack(original);

        //comparing String values and references
        int count = 0;
        while (!original.empty()) {
            String originalVal = original.pop();
            String copyVal = copy.pop();

            //comparison
            String equalValue;
            if (originalVal.equals(copyVal)) {
                equalValue = "Equal values";
            } else {
                equalValue = "Unequal values";
            }
            String equalReference;
            if (originalVal == copyVal) {
                equalReference = "equal reference";
            } else {
                equalReference = "unequal reference";
            }

            //out: result
            System.out.println("Element " + count + ":  " + equalValue + " and " + equalReference);
            count++;
        }


    }

}
