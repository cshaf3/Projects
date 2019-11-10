package cs445.lab7;

public class SieveOfEratosthenes {
    public static ListInterface<Integer> primesUpTo(int max) {
        ListInterface<Integer> primes = new ArrayList<>();
        boolean prime;
        for(int i = 0; i <= max; i++)
        {
            // if(i%j!=0)
            // {
            //     primes.add(i);
            // }
            for(int j = 2; j <= max; j++)
            {
                if(i%j!=0)
                {
                    prime = true;
                }
            }
            if(prime = true)
            {
                primes.add(i);
            }
            // System.out.println(j);
        }
        return primes;
    }

    public static void main(String[] args) {
        int end = 0;
        try {
            end = new Integer(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        }

        ListInterface<Integer> result = primesUpTo(end);
        if (result != null) {
            System.out.println("Primes:");
            for (int i = 0; i < result.getSize(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
        } else {
            System.out.println("primesUpTo() returned null. Did you complete it?");
        }
    }
}
