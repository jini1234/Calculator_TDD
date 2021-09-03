public class StringCalculator {

    public int Add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        } else {
            String[] numsString = numbers.split(",");
            int sum = 0;
            if(numbers.length()==1){
                sum=Integer.parseInt(numsString[0]);
            }
            else {
                sum = Integer.parseInt(numsString[0]) + Integer.parseInt(numsString[1]);
            }
            return sum;
        }
    }
}
