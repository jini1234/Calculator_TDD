public class StringCalculator {
    private int calledCount = 0;

    public int Add(String numbers) {
        calledCount++;
        if (numbers.equals("")) {
            return 0;
        } else {
            String delimiter = ",";
            if (numbers.startsWith("//")) {
                delimiter = getDelimiter(numbers);
                numbers = getNumbers(numbers);
            }
            String[] numsString = numbers.split(delimiter + "|\n");
            String negs = getNegNumbers(numsString);
            if (!negs.isEmpty()) {
                throw new IllegalArgumentException("negatives not allowed:" + negs);
            }
            return getSum(numsString);
        }
    }

    private int getSum(String[] numsString) {
        int sum = 0;
        for (String num : numsString) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

    public int GetCalledCount() {
        return calledCount;
    }

    private String getNegNumbers(String[] numsString) {
        String negs = "";
        for (String num : numsString) {
            if (isNeg(num)) {
                if (negs.isEmpty()) {
                    negs += num;
                } else {
                    negs += ("," + num);
                }
            }
        }
        return negs;
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
