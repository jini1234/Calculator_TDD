import java.util.regex.Pattern;

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
            if (Integer.parseInt(num) > 1000) {
                continue;
            }
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
        if (numbers.charAt(2) == '[') {
            return extractDelim(numbers, 3);
        }
        return numbers.substring(2, numbers.indexOf("\n"));
    }

    private String extractDelim(String numbers, int index) {
        String delim = "";
        String newDelim = "";
        while (numbers.charAt(index) != '\n') {
            if (numbers.charAt(index) == '[') {
                newDelim = "";
            } else if (numbers.charAt(index) == ']') {
                delim = appendToDelimiter(delim, newDelim);
            } else {
                newDelim += numbers.charAt(index);
            }
            index++;
        }
        return delim;
    }

    private String appendToDelimiter(String delim, String newDelim) {
        if (delim.isEmpty()) {
            delim += Pattern.quote(newDelim);
        } else {
            delim += ("|" + Pattern.quote(newDelim));
        }
        return delim;
    }
}
