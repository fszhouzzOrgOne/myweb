package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MultitaskService {

    private static ExecutorService executor = new ThreadPoolExecutor(5, 11, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3));
    
    public static void main(String[] args) throws Exception {
        List<SleepingCallable> calls = new ArrayList<SleepingCallable>();
        calls.add(new SleepingCallable(1));
        calls.add(new SleepingCallable(3));
        calls.add(new SleepingCallable(7));
        calls.add(new SleepingCallable(9));
        Long timeout = 5000L;
        Boolean timeoutReturnNull = true;
        List<String> res = MultitaskService.syncInvokeAll(calls, timeout, timeoutReturnNull);
        System.out.println(res);

        timeout = 10 * 1000L;
        timeoutReturnNull = false;
        res = MultitaskService.syncInvokeAll(calls, timeout, timeoutReturnNull);
        System.out.println(res);

        try {
            timeout = 5000L;
            timeoutReturnNull = false;
            res = MultitaskService.syncInvokeAll(calls, timeout, timeoutReturnNull);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static private class SleepingCallable implements Callable<String> {
        private int second;
        public SleepingCallable(int second) {
            this.second = second;
        }
        @Override
        public String call() throws Exception {
            Thread.sleep(second * 1000L);
            return "Get up! I Slept for " + second;
        }
        
    }
    
    public static <T> List<T> syncInvokeAll(List<? extends Callable<T>> calls, Long timeout, Boolean timeoutReturnNull) throws Exception {
        if (null == calls || calls.isEmpty()) {
            return null;
        }
        List<Future<T>> futures = null;
        if (null != timeout) {
            if (null == timeoutReturnNull) {
                timeoutReturnNull = false;
            }
            futures = executor.invokeAll(calls, timeout, TimeUnit.MILLISECONDS);
        } else {
            futures = executor.invokeAll(calls);
        }
        List<T> res = new ArrayList<T>();
        int index = 1;
        for (Future<T> future : futures) {
            if (future.isCancelled() || !future.isDone()) {
                if (timeoutReturnNull) {
                    res.add(null);
                } else {
                    throw new TimeoutException(
                            "任务" + index + "执行超时，timeout=" + timeout);
                }
            } else {
                res.add(future.get());
            }
            index++;
        }
        return res;
    }
}
