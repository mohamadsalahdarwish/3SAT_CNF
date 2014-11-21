package ProfilingStuff;

import java.util.Arrays;

/**
 * Created by JiaHao on 21/11/14.
 */
public class FixFile {


    public static void main(String[] args) {

        String a = "-1     -7    0\n" +
                "-1     -13   0\n" +
                "-1     -19   0\n" +
                "-1     -25   0\n" +
                "-1     -31   0\n" +
                "-1     -37   0\n" +
                "-7     -13   0\n" +
                "-7     -19   0\n" +
                "-7     -25   0\n" +
                "-7     -31   0\n" +
                "-7     -37   0\n" +
                "-13    -19   0\n" +
                "-13    -25   0\n" +
                "-13    -31   0\n" +
                "-13    -37   0\n" +
                "-19    -25   0\n" +
                "-19    -31   0\n" +
                "-19    -37   0\n" +
                "-25    -31   0\n" +
                "-25    -37   0\n" +
                "-31    -37   0\n" +
                "-2     -8    0\n" +
                "-2     -14   0\n" +
                "-2     -20   0\n" +
                "-2     -26   0\n" +
                "-2     -32   0\n" +
                "-2     -38   0\n" +
                "-8     -14   0\n" +
                "-8     -20   0\n" +
                "-8     -26   0\n" +
                "-8     -32   0\n" +
                "-8     -38   0\n" +
                "-14    -20   0\n" +
                "-14    -26   0\n" +
                "-14    -32   0\n" +
                "-14    -38   0\n" +
                "-20    -26   0\n" +
                "-20    -32   0\n" +
                "-20    -38   0\n" +
                "-26    -32   0\n" +
                "-26    -38   0\n" +
                "-32    -38   0\n" +
                "-3     -9    0\n" +
                "-3     -15   0\n" +
                "-3     -21   0\n" +
                "-3     -27   0\n" +
                "-3     -33   0\n" +
                "-3     -39   0\n" +
                "-9     -15   0\n" +
                "-9     -21   0\n" +
                "-9     -27   0\n" +
                "-9     -33   0\n" +
                "-9     -39   0\n" +
                "-15    -21   0\n" +
                "-15    -27   0\n" +
                "-15    -33   0\n" +
                "-15    -39   0\n" +
                "-21    -27   0\n" +
                "-21    -33   0\n" +
                "-21    -39   0\n" +
                "-27    -33   0\n" +
                "-27    -39   0\n" +
                "-33    -39   0\n" +
                "-4     -10   0\n" +
                "-4     -16   0\n" +
                "-4     -22   0\n" +
                "-4     -28   0\n" +
                "-4     -34   0\n" +
                "-4     -40   0\n" +
                "-10    -16   0\n" +
                "-10    -22   0\n" +
                "-10    -28   0\n" +
                "-10    -34   0\n" +
                "-10    -40   0\n" +
                "-16    -22   0\n" +
                "-16    -28   0\n" +
                "-16    -34   0\n" +
                "-16    -40   0\n" +
                "-22    -28   0\n" +
                "-22    -34   0\n" +
                "-22    -40   0\n" +
                "-28    -34   0\n" +
                "-28    -40   0\n" +
                "-34    -40   0\n" +
                "-5     -11   0\n" +
                "-5     -17   0\n" +
                "-5     -23   0\n" +
                "-5     -29   0\n" +
                "-5     -35   0\n" +
                "-5     -41   0\n" +
                "-11    -17   0\n" +
                "-11    -23   0\n" +
                "-11    -29   0\n" +
                "-11    -35   0\n" +
                "-11    -41   0\n" +
                "-17    -23   0\n" +
                "-17    -29   0\n" +
                "-17    -35   0\n" +
                "-17    -41   0\n" +
                "-23    -29   0\n" +
                "-23    -35   0\n" +
                "-23    -41   0\n" +
                "-29    -35   0\n" +
                "-29    -41   0\n" +
                "-35    -41   0\n" +
                "-6     -12   0\n" +
                "-6     -18   0\n" +
                "-6     -24   0\n" +
                "-6     -30   0\n" +
                "-6     -36   0\n" +
                "-6     -42   0\n" +
                "-12    -18   0\n" +
                "-12    -24   0\n" +
                "-12    -30   0\n" +
                "-12    -36   0\n" +
                "-12    -42   0\n" +
                "-18    -24   0\n" +
                "-18    -30   0\n" +
                "-18    -36   0\n" +
                "-18    -42   0\n" +
                "-24    -30   0\n" +
                "-24    -36   0\n" +
                "-24    -42   0\n" +
                "-30    -36   0\n" +
                "-30    -42   0\n" +
                "-36    -42   0\n" +
                " 6      5      4      3      2      1    0\n" +
                " 12     11     10     9      8      7    0\n" +
                " 18     17     16     15     14     13   0\n" +
                " 24     23     22     21     20     19   0\n" +
                " 30     29     28     27     26     25   0\n" +
                " 36     35     34     33     32     31   0\n" +
                " 42     41     40     39     38     37   0\n";

        int counter = 0;

        int[] myMap = new int[250];

        for (int i = 1; i < 200; i++) {
            if (a.contains(Integer.toString(i))) {
                myMap[i] = 1;
            }
        }

        for (int i : myMap ){
            if (i != 0) {
                counter++;
            }
        }
        System.out.println(Arrays.toString(myMap));
        System.out.println(counter);
    }


}



