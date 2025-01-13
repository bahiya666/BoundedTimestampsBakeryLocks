import java.util.Arrays;

public class BoundedTimestamp {
    public final int[] timestamp;

    public BoundedTimestamp(int[] timestampArray) {
        this.timestamp = timestampArray;
    }

    public BoundedTimestamp(int n) {
        this.timestamp = new int[n];
        //explicitly set new timestamp to 0,0,0...
        for (int i = 0; i < n; i++) {
            timestamp[i] = 0;
        }
    }

    public int[] getTime() {
        return timestamp.clone();
    }

    // Returns 1 if this comes before the other (i.e this is earlier), -1 if this comes after the other (i.e this is later), 0 if they are equal
    public int compare(BoundedTimestamp other) {
        for (int i = 0; i < this.timestamp.length; i++) {
            int thisValue = this.timestamp[i];
            int otherValue = other.timestamp[i];

            if (thisValue == otherValue) {
                continue; // If the current digits are equal, move to the next digit
            }

            // Implementing the circular comparison: 0 < 1 < 2 < 0
            if ((thisValue == 0 && otherValue == 1) ||
                (thisValue == 1 && otherValue == 2) ||
                (thisValue == 2 && otherValue == 0)) {
                return 1; // This timestamp comes before the other
            } else {
                return -1; // This timestamp comes after the other
            }
        }
        return 0; // If all digits are the same, the timestamps are equal
    

    }
     // Increment this timestamp following the ternary logic (0 -> 1 -> 2 -> 0)
     private static BoundedTimestamp[] excludeIndex(BoundedTimestamp[] labels, int index) {
        BoundedTimestamp[] result = new BoundedTimestamp[labels.length - 1];
        int count = 0;
        for (int i = 0; i < labels.length; i++) {
            if (i != index) {
                result[count++] = labels[i];
            }
        }
        return result;
    }

    // Find the maximum timestamp in an array
    private static BoundedTimestamp findMaximum(BoundedTimestamp[] timestamps) {
        BoundedTimestamp max = timestamps[0];
        for (int i = 1; i < timestamps.length; i++) {
            if (max.compare(timestamps[i]) == 1) {
                max = timestamps[i];
            }
        }
        return new BoundedTimestamp(max.timestamp);
    }

    // Increment the timestamp considering the circular nature
    private static BoundedTimestamp increment(BoundedTimestamp timestamp) {
        int[] newTimestamp = timestamp.timestamp.clone();
        for (int i = newTimestamp.length - 1; i >= 0; i--) {
            newTimestamp[i] = (newTimestamp[i] + 1) % 3;
            if (newTimestamp[i] != 0) {
                break;
            } if (i == 0)
            {
                for (int j=0; j<newTimestamp.length; j++)
                {
                    newTimestamp[j]=0;
                }
            }
        }
        return new BoundedTimestamp(newTimestamp);
    }

    // Check if the timestamp exists in an array
    private static boolean existsIn(BoundedTimestamp timestamp, BoundedTimestamp[] others) {
        for (BoundedTimestamp other : others) {
            if (timestamp.compare(other) == 0) {
                return true;
            }
        }
        return false;
    }

    // Check if the timestamp is smaller than all in the array
    private static boolean isSmallerThanAll(BoundedTimestamp timestamp, BoundedTimestamp[] others) {
        for (BoundedTimestamp other:others)
        {
            if (timestamp.compare(other)!=-1)
            {
                return false;
            }
        }
        return true;
    }
    // Find the next valid timestamp for the given index (thread) based on the others
    public static BoundedTimestamp getNext(BoundedTimestamp[] labels, int indexAskingForLabel) {
        BoundedTimestamp[] others = excludeIndex(labels, indexAskingForLabel);
        BoundedTimestamp max = findMaximum(others);
        BoundedTimestamp next = increment(max);

        while (existsIn(next, others) || !isSmallerThanAll(next, others)) {
            next = increment(next);
        }

        return next;
    }

    // Override equals for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BoundedTimestamp that = (BoundedTimestamp) obj;
        return Arrays.equals(timestamp, that.timestamp);
    }
       public int[] getValues() {
        return timestamp.clone(); // Return a copy of the values array to preserve encapsulation
    }


    //you may add varibles and methods as needed

    @Override
    public String toString() {
        return java.util.Arrays.toString(timestamp);
    }

}
