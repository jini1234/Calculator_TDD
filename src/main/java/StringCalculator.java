public class StringCalculator {
    public int Add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        } else {
            String[] numsString = numbers.split(",");
            int sum = 0;
            for (String num : numsString) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }
}
