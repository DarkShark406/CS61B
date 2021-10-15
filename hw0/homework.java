public class homework {
    public static void drawTriangle(int n){
        for (int i=0;i<n;i++){
            for (int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static int max(int[] m){
        int max = m[0];
        for (int i=1;i<m.length;i++){
            if (m[i] > max)
                max = m[i];
        }
        return max;
    }

    public static void windowPosSum(int[] a, int n){
        for (int i=0;i<a.length-1;i++){
            if (a[i] > 0){
                for (int j=i+1;j<=i+n && j < a.length;j++){
                    a[i] += a[j];
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
