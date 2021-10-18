package wgjd2ed;

/**
 * Java 9 GetPID class
 *
 * @author ben
 */
public class GetPID {
    public static long getPid() {
        var ph = ProcessHandle.current();
        return ph.pid();
    }
}
