public class MainTransaction
{
    public static void main(String[] args)
    {
        Runnable query_obj1 = new concurrentTransaction1();
        Runnable query_obj2 = new concurrentTransaction2();
        Thread T1 = new Thread(query_obj1);
        Thread T2 = new Thread(query_obj2);

        T1.setPriority(Thread.MAX_PRIORITY);
        T2.setPriority(Thread.MIN_PRIORITY);

        T1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T2.start();

    }
}
