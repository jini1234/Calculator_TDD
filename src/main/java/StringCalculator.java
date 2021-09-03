public class StringCalculator {
    public int Add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        } else {
            String delimiter = ",";
            if (numbers.startsWith("//")) {
                delimiter = getDelimiter(numbers);
                numbers = getNumbers(numbers);
            }

            String[] numsString = numbers.split(delimiter + "|\n");
            int sum = 0;
            for (String num : numsString) {
                if (isNeg(num)) {
                    throw new IllegalArgumentException("negatives not allowed:" + num);
                }
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }

    private boolean isNeg(String num) {
        return Integer.parseInt(num) < 0;
    }

    private String getNumbers(String numbers) {
        return numbers.substring(numbers.indexOf("\n") + 1);
    }

    private String getDelimiter(String numbers) {
        return numbers.substring(2, numbers.indexOf("\n"));
    }
}
