public class HelloWorld {

    private static class Heap {

        private int limit, size;
        private int[] arr;

        public Heap(int n) {
            this.limit=n;
            this.arr=new int[n];
            this.size=0;
        }

        private boolean add(int val) {
            if (size>=limit) return false;
            size++;
            arr[size]=val;
            // swap upwards
            int i=size;
            while (i>1) {
                if (arr[i]<arr[i/2]) { 
                    swap(arr, i, i/2);                
                    i=i/2;
                } else break;
            }
            // printArray();
            return true;
        }

        private int remove() {
            if (size==0) return -1;
            int res=arr[1];
            swap(arr, 1, size);
            size--;
            // swap downwards
            int i=1;
            while (2*i<=size) {
                int left=2*i, right=2*i+1;
                if (arr[i]>arr[left] || (right<=size && arr[i]>arr[right])) {
                    int idx=left;
                    if (right<=size && arr[right]<arr[left]) idx=right;
                    swap(arr, idx, i);
                    i=idx;
                } else break;
            }
            // printArray();
            return res;
        }
        
        private void swap(int[] a, int i, int j) {
            int tmp=a[i];
            a[i]=a[j];
            a[j]=tmp;
        }
        
        private boolean isEmpty() {
            return size==0;
        }
        
        private void printArray() {
            for (int i=1; i<=size; ++i) {
                System.out.printf("%d ", arr[i]);
            }
            System.out.printf("\n");
        }
    
    }

    public static void main(String []args){
        Heap h=new Heap(10);
        h.add(0);
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(7);
        h.add(5);
        h.add(6);
        h.add(1);
        while (!h.isEmpty()) {
            System.out.println(h.remove());
        }
    }
    
}

