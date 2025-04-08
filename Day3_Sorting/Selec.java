class Selec {
    public static void main(String[] args) {
            int[] arr = { 8, 6, 4, 1, 9, 7, 6, 2, 4, 5, 4, 1};
            int i, Si,j;
           int n=arr.length;
            for (i = 0; i < n-1; i++) {
                Si = i;
                for (j = i+1; j < n; j++) {
                    if (arr[j] < arr[Si]) {
                        Si = j;
                    }
                }
                int temp = arr[Si];
                arr[Si]=arr[i];
                arr[i]=temp;
            }
            for(i=0;i<n;i++){
                System.out.print(arr[i]+" ");
            }
            //System.out.println(arr);
        }
    }