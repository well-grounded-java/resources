package ch05;

public class SimpleLifecycle {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            var start = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            var thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() +
                    " slept for "+ (System.currentTimeMillis() - start));
        };
        var t = new Thread(r);
        t.setName("Worker");
        t.start();
        Thread.sleep(100);
        t.join();
        System.out.println("Exiting");
    }
}
