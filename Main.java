import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args)throws InterruptedException  {
        //testCompare();
        testGetNext();
        testGetNext2();
        //testWithLock();
    }

    public static void testWithLock() throws InterruptedException {
        int threads = 4;
        Marker[] markers = new Marker[threads];
        Queue<Paper> allPapers = new LinkedList<>();
        System.out.println("###");
        for (int j = 0; j < 100; j++) {
    
            BackeryLock lock = new BackeryLock(threads);
            for (int i = 0; i < 50; i++) {
                allPapers.add(new Paper());
            }
            for (int i = 0; i < markers.length; i++) {
                markers[i] = new Marker(lock, allPapers);
            }
            for (int i = 0; i < markers.length; i++) {
                System.out.println("Starting Marker " + i + " in Iteration " + j);
                markers[i].start();
            }
            for (int i = 0; i < markers.length; i++) {
                markers[i].join();
                System.out.println("Joined Marker " + i + " in Iteration " + j);
            }
            System.out.println("Output " + j);
        }
    }
    


public static void testCompare() 
{
    BoundedTimestamp ts1 = new BoundedTimestamp(new int[]{0, 1});
    BoundedTimestamp ts2 = new BoundedTimestamp(new int[]{1, 0});
    BoundedTimestamp ts3 = new BoundedTimestamp(new int[]{2, 2, 1});
    BoundedTimestamp ts4 = new BoundedTimestamp(new int[]{2, 0, 2});
    BoundedTimestamp ts5 = new BoundedTimestamp(new int[]{1, 0, 0});
    BoundedTimestamp ts6 = new BoundedTimestamp(new int[]{2, 2, 1});

    System.out.println("Test 1: " + (ts1.compare(ts2) == 1));  // Expected: true
    System.out.println("Test 2: " + (ts2.compare(ts1) == -1)); // Expected: true
    System.out.println("Test 3: " + (ts3.compare(ts4) == 1));  // Expected: true
    System.out.println("Test 4: " + (ts3.compare(ts5) == -1)); // Expected: true
    System.out.println("Test 5: " + (ts3.compare(ts6) == 0));  // Expected: true
        
        BoundedTimestamp t1 = new BoundedTimestamp(new int[]{0, 1});
        BoundedTimestamp t2 = new BoundedTimestamp(new int[]{1, 0});
        BoundedTimestamp t3 = new BoundedTimestamp(new int[]{2, 1});
        BoundedTimestamp t4 = new BoundedTimestamp(new int[]{2, 2});
        BoundedTimestamp t5 = new BoundedTimestamp(new int[]{0, 0});
        BoundedTimestamp t6 = new BoundedTimestamp(new int[]{1, 1});
        BoundedTimestamp t7 = new BoundedTimestamp(new int[]{2, 2});

        assert t1.compare(t2) == -1 : "Test 1 failed"; // [0,1] < [1,0]
        assert t2.compare(t1) == 1 : "Test 2 failed";  // [1,0] > [0,1]
        assert t3.compare(t4) == -1 : "Test 3 failed"; // [2,1] < [2,2]
        assert t4.compare(t3) == 1 : "Test 4 failed";  // [2,2] > [2,1]
        assert t5.compare(t6) == -1 : "Test 5 failed"; // [0,0] < [1,1]
        assert t6.compare(t5) == 1 : "Test 6 failed";  // [1,1] > [0,0]
        assert t7.compare(t7) == 0 : "Test 7 failed";  // [2,2] == [2,2]

        System.out.println("All compare tests passed.");
}


public static void testGetNext() 
{
   // Example 1
   int numThreads1 = 3;
   BoundedTimestamp[] labels1 = new BoundedTimestamp[numThreads1];
   labels1[0] = new BoundedTimestamp(new int[]{0, 0});
   labels1[1] = new BoundedTimestamp(new int[]{0, 0});
   labels1[2] = new BoundedTimestamp(new int[]{0, 1});
   int indexAskingForLabel1 = 1; // Thread 1 requesting the next timestamp

   System.out.println("Example 1:");
   printLabels(labels1);
   BoundedTimestamp nextTimestamp1 = BoundedTimestamp.getNext(labels1, indexAskingForLabel1);
   System.out.println("Next timestamp for thread " + indexAskingForLabel1 + ": " + nextTimestamp1);
   System.out.println();

   // Example 2
   int numThreads2 = 3;
   BoundedTimestamp[] labels2 = new BoundedTimestamp[numThreads2];
   labels2[0] = new BoundedTimestamp(new int[]{2, 0});
   labels2[1] = new BoundedTimestamp(new int[]{1, 1});
   labels2[2] = new BoundedTimestamp(new int[]{2, 1});
   int indexAskingForLabel2 = 0; // Thread 0 requesting the next timestamp

   System.out.println("Example 2:");
   printLabels(labels2);
   BoundedTimestamp nextTimestamp2 = BoundedTimestamp.getNext(labels2, indexAskingForLabel2);
   System.out.println("Next timestamp for thread " + indexAskingForLabel2 + ": " + nextTimestamp2);
}

private static void printLabels(BoundedTimestamp[] labels) {
   for (int i = 0; i < labels.length; i++) {
       System.out.println("Label[" + i + "]: " + labels[i]);
   }
}


    public static void testGetNext2() {
        // Example 1
        int numThreads1 = 3;
        BoundedTimestamp[] labels1 = new BoundedTimestamp[numThreads1];
        labels1[0] = new BoundedTimestamp(new int[]{0, 0});
        labels1[1] = new BoundedTimestamp(new int[]{0, 0});
        labels1[2] = new BoundedTimestamp(new int[]{0, 0});
        int indexAskingForLabel1 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 1:");
        printLabels(labels1);
        BoundedTimestamp nextTimestamp1 = BoundedTimestamp.getNext(labels1, indexAskingForLabel1);
        System.out.println("Next timestamp for thread " + indexAskingForLabel1 + ": " + nextTimestamp1);
        System.out.println();

        // Example 2
        int numThreads2 = 3;
        BoundedTimestamp[] labels2 = new BoundedTimestamp[numThreads2];
        labels2[0] = new BoundedTimestamp(new int[]{0, 0});
        labels2[1] = new BoundedTimestamp(new int[]{0, 1});
        labels2[2] = new BoundedTimestamp(new int[]{0, 0});
        int indexAskingForLabel2 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 2:");
        printLabels(labels2);
        BoundedTimestamp nextTimestamp2 = BoundedTimestamp.getNext(labels2, indexAskingForLabel2);
        System.out.println("Next timestamp for thread " + indexAskingForLabel2 + ": " + nextTimestamp2);
        System.out.println();

        // Example 3
        int numThreads3 = 3;
        BoundedTimestamp[] labels3 = new BoundedTimestamp[numThreads3];
        labels3[0] = new BoundedTimestamp(new int[]{0, 0});
        labels3[1] = new BoundedTimestamp(new int[]{1, 0});
        labels3[2] = new BoundedTimestamp(new int[]{0, 0});
        int indexAskingForLabel3 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 3:");
        printLabels(labels3);
        BoundedTimestamp nextTimestamp3 = BoundedTimestamp.getNext(labels3, indexAskingForLabel3);
        System.out.println("Next timestamp for thread " + indexAskingForLabel3 + ": " + nextTimestamp3);
        System.out.println();

        // Example 4
        int numThreads4 = 3;
        BoundedTimestamp[] labels4 = new BoundedTimestamp[numThreads4];
        labels4[0] = new BoundedTimestamp(new int[]{0, 0});
        labels4[1] = new BoundedTimestamp(new int[]{1, 1});
        labels4[2] = new BoundedTimestamp(new int[]{0, 0});
        int indexAskingForLabel4 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 4:");
        printLabels(labels4);
        BoundedTimestamp nextTimestamp4 = BoundedTimestamp.getNext(labels4, indexAskingForLabel4);
        System.out.println("Next timestamp for thread " + indexAskingForLabel4 + ": " + nextTimestamp4);
        System.out.println();

        // Example 5
        int numThreads5 = 3;
        BoundedTimestamp[] labels5 = new BoundedTimestamp[numThreads5];
        labels5[0] = new BoundedTimestamp(new int[]{0, 0});
        labels5[1] = new BoundedTimestamp(new int[]{1, 2});
        labels5[2] = new BoundedTimestamp(new int[]{0, 0});
        int indexAskingForLabel5 = 2; // Thread 2 requesting the next timestamp

        System.out.println("Example 5:");
        printLabels(labels5);
        BoundedTimestamp nextTimestamp5 = BoundedTimestamp.getNext(labels5, indexAskingForLabel5);
        System.out.println("Next timestamp for thread " + indexAskingForLabel5 + ": " + nextTimestamp5);
        System.out.println();

        // Example 6
        int numThreads6 = 3;
        BoundedTimestamp[] labels6 = new BoundedTimestamp[numThreads6];
        labels6[0] = new BoundedTimestamp(new int[]{0, 0});
        labels6[1] = new BoundedTimestamp(new int[]{1, 2});
        labels6[2] = new BoundedTimestamp(new int[]{1, 0});
        int indexAskingForLabel6 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 6:");
        printLabels(labels6);
        BoundedTimestamp nextTimestamp6 = BoundedTimestamp.getNext(labels6, indexAskingForLabel6);
        System.out.println("Next timestamp for thread " + indexAskingForLabel6 + ": " + nextTimestamp6);
        System.out.println();

        // Example 7
        int numThreads7 = 3;
        BoundedTimestamp[] labels7 = new BoundedTimestamp[numThreads7];
        labels7[0] = new BoundedTimestamp(new int[]{0, 0});
        labels7[1] = new BoundedTimestamp(new int[]{1, 1});
        labels7[2] = new BoundedTimestamp(new int[]{1, 0});
        int indexAskingForLabel7 = 2; // Thread 2 requesting the next timestamp

        System.out.println("Example 7:");
        printLabels(labels7);
        BoundedTimestamp nextTimestamp7 = BoundedTimestamp.getNext(labels7, indexAskingForLabel7);
        System.out.println("Next timestamp for thread " + indexAskingForLabel7 + ": " + nextTimestamp7);
        System.out.println();

        // Example 8
        int numThreads8 = 3;
        BoundedTimestamp[] labels8 = new BoundedTimestamp[numThreads8];
        labels8[0] = new BoundedTimestamp(new int[]{0, 0});
        labels8[1] = new BoundedTimestamp(new int[]{1, 2});
        labels8[2] = new BoundedTimestamp(new int[]{2, 0});
        int indexAskingForLabel8 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 8:");
        printLabels(labels8);
        BoundedTimestamp nextTimestamp8 = BoundedTimestamp.getNext(labels8, indexAskingForLabel8);
        System.out.println("Next timestamp for thread " + indexAskingForLabel8 + ": " + nextTimestamp8);
        System.out.println();

        // Example 9
        int numThreads9 = 3;
        BoundedTimestamp[] labels9 = new BoundedTimestamp[numThreads9];
        labels9[0] = new BoundedTimestamp(new int[]{1, 1});
        labels9[1] = new BoundedTimestamp(new int[]{2, 1});
        labels9[2] = new BoundedTimestamp(new int[]{2, 2});
        int indexAskingForLabel9 = 1; // Thread 1 requesting the next timestamp

        System.out.println("Example 9:");
        printLabels(labels9);
        BoundedTimestamp nextTimestamp9 = BoundedTimestamp.getNext(labels9, indexAskingForLabel9);
        System.out.println("Next timestamp for thread " + indexAskingForLabel9 + ": " + nextTimestamp9);
        System.out.println();

        // Example 10
        int numThreads10 = 3;
        BoundedTimestamp[] labels10 = new BoundedTimestamp[numThreads10];
        labels10[0] = new BoundedTimestamp(new int[]{1, 1});
        labels10[1] = new BoundedTimestamp(new int[]{2, 0});
        labels10[2] = new BoundedTimestamp(new int[]{2, 1});
        int indexAskingForLabel10 = 2; // Thread 2 requesting the next timestamp

        System.out.println("Example 10:");
        printLabels(labels10);
        BoundedTimestamp nextTimestamp10 = BoundedTimestamp.getNext(labels10, indexAskingForLabel10);
        System.out.println("Next timestamp for thread " + indexAskingForLabel10 + ": " + nextTimestamp10);
        System.out.println();
    }

}




