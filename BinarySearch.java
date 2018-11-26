package Done;

public class BinarySearch {

    // inv: x, arr - immutable
    // Pre: ∀i = 0...arr.length - 2: arr[i] >= arr[i + 1] && arr != null
    // Post: ((∃ i ∈ [0; arr.length) : arr[i] <= x && R == min(i|arr[i] <= x) ||
    // (∄ i ∈ [0; arr.length) : arr[i] <= x && R == arr.length))
    private static int iterativeSearch(int x, int[] arr) {
        int l = 0;
        int r = arr.length;
        int mid;
        // inv: r' >= l' && R ∈ [l; r]
        while (r > l) {
            mid = l + (r - l) / 2;
            if (arr[mid] > x) {
                // l < r && r' == r && l' = mid + 1 && ∀i = 0...mid arr[i] > x
                l = mid + 1;
            } else {
                // l < r && l' == l && r' = mid && ∀i = mid...arr.length arr[i] <= x
                r = mid;
            }
        }
        return l;
    }

    // inv: x, arr - immutable
    // Pre: ∀i = 0...arr.length - 2: arr[i] >= arr[i + 1] && arr != null && R ∈ [l; r) && r >= l
    // Post: ((∃ i ∈ [l; r) : arr[i] <= x && R == min(i|arr[i] <= x) ||
    // (∄ i ∈ [l; r) : arr[i] <= x && R == r))
    private static int recursiveSearch (int x, int[] arr, int l, int r) {
        int mid = l + (r - l) / 2;
        if (l >= r) {
            // r == l &&
            // ((arr[r] > x && r' == r && l' == r) ||
            // (∃ i ∈ [l; r) : arr[i] <= x && l == min(i|arr[i] <= x))
            return l;
        } else if (arr[mid] > x) {
            // l < r && r' == r && l' = mid + 1 && ∀i = 0...mid + 1: arr[i] > x
            return recursiveSearch(x, arr, mid + 1, r);
        } else {
            // l < r && l' == l && r' = mid && ∀i = mid...r: arr[i] <= x
            return recursiveSearch(x, arr, l, mid);
        }
    }


    public static void main(String[] args) {
        int[] array;
        int numToFind;
        if (args.length == 0) {
                System.out.println("Wrong amount of arguments");
        } else {
            try {
                numToFind = Integer.parseInt(args[0]);
                array = new int[args.length-1];
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(args[i + 1]);
                }
                System.out.println(iterativeSearch(numToFind, array));
//                System.out.println(recursiveSearch(numToFind, array, 0, array.length));
            } catch (NumberFormatException e) {
                System.out.println("Not a number among arguments found: " + e.getMessage());
            }

        }
    }
}