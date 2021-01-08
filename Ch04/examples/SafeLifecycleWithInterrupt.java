package examples;

public class SafeLifecycleWithInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            var start = System.currentTimeMillis();
            var wasInterrupted = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                wasInterrupted = true;
                e.printStackTrace();
            }
            var thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() +
                    " slept for "+ (System.currentTimeMillis() - start));
            if (wasInterrupted) {
                System.out.println("Thread "+ thisThread.getName() +" was interrupted");
            }
        };
        var t = new Thread(r);
        t.setName("Worker");
        t.start();
        Thread.sleep(100);
        t.interrupt();
        t.join();
        System.out.println("Exiting");
    }
}
