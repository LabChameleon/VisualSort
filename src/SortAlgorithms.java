import java.util.Random;

/**
 * Created by Julian on 04.02.2015.
 */
public class SortAlgorithms
{
    AnimationFrame window;
    int comparisons;
    int permutations;
    int sleepTime;

    SortAlgorithms(AnimationFrame window, int arrayLength, String sortAnimation, int sleepTime)
    {
        int a[] = new int[arrayLength];

        fuelleArray(a);

        this.window = window;
        this.comparisons = 0;
        this.permutations = 0;
        this.sleepTime = sleepTime;

        this.window.update(a, comparisons, permutations);

        if(sortAnimation == "Quicksort")
        {
            quickSort_rek(a, 0, a.length - 1);
        }
        else if(sortAnimation == "Bubblesort")
        {
            bubbleSort(a);
        }
        else if(sortAnimation == "Insertionsort")
        {
            insertSort(a);
        }
        else {
            if (sortAnimation == "Selectionsort") {
                selectSort(a);
            } else {
                System.out.println("FAIL");
            }
        }
    }

    public void fuelleArray(int a[])
    {
        Random random = new Random();

        for(int i = 0; i < a.length; i++)
        {
            a[i] = i;
        }

        for(int i = 0; i < a.length; i++)
        {
            int index = random.nextInt(a.length);

            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
    }

    public void quickSort_rek(int[] a, int start, int end)
    {
        int pivot = start;

        int kleiner = start + 1;
        int groesser = end;

        boolean partSorted = false;

        if(a.length == 0 || a.length == 1)
        {
            return;
        }

        while(start < end && partSorted == false)
        {
            window.update(a, comparisons, permutations);

            comparisons++;

            if(kleiner > groesser)
            {
                int temp = a[pivot];
                a[pivot] = a[groesser];
                a[groesser] = temp;

                permutations++;

                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                quickSort_rek(a, start, groesser - 1);

                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                quickSort_rek(a, groesser + 1, end);

                partSorted = true;
            }
            else if(kleiner == groesser && a[pivot] > a[kleiner])
            {
                comparisons++;

                int temp = a[pivot];
                a[pivot] = a[groesser];
                a[groesser] = temp;

                permutations++;


                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                quickSort_rek(a, start, groesser-1);

                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                quickSort_rek(a, groesser+1, end);

                partSorted = true;
            }
            else if(a[kleiner] < a[pivot])
            {
                comparisons += 2;

                kleiner++;
            }

            else if(a[groesser] > a[pivot])
            {
                comparisons += 3;

                groesser--;
            }
            else
            {
                comparisons += 4;

                int temp = a[kleiner];
                a[kleiner] = a[groesser];
                a[groesser] = temp;
                groesser--;

                permutations++;
                window.update(a, comparisons, permutations);

                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void bubbleSort(int[] a)
    {
        int temp;
        for(int i = 1; i < a.length; i++)
        {
            window.update(a, comparisons, permutations);

            for(int j = 0; j < a.length - i; j++)
            {
                comparisons++;

                if(a[j] > a[j+1])
                {
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1]=temp;

                    permutations++;
                    window.update(a, comparisons, permutations);
                    try {
                        Thread.sleep(sleepTime);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

            }
        }
    }

    public void insertSort(int[] a)
    {
        for (int k = 1; k < a.length; k++)
        {
            int x = a[k];
            int i = k;

            while (i > 0 && (a[i-1] > x))
            {
                comparisons++;

                a[i] = a[i-1];

                permutations++;

                i--;
            }

            comparisons++;

            a[i] = x;

            window.update(a, comparisons, permutations);
            try {
                Thread.sleep(sleepTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void selectSort(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            int min = i; // Stelle des geringsten Wertes
            for (int j = i+1; j < a.length; j++)
            {
                comparisons++;

                if (a[j] < a[min])
                {
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;

            permutations++;

            window.update(a, comparisons, permutations);
            try {
                Thread.sleep(sleepTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
