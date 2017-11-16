public class test
{
    public static void main(String[] args) {    
        int x = 10;
        int a = x - 1;
        int b = x - 2;

        while (x > 0) {
            System.out.println(a*b - x);
            x = x - 1;
        }
        System.out.println(a*b);
    }

    public void range_tests(){
        int x = 10, a, b, c;
        a = x + 14;
        b = a - 9;
        c = b * 3;
        x = c / 3;
    }
}
