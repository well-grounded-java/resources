package wgjd.discovery.internal;

import java.io.PrintStream;

public class PlainAttachOutput implements AttachOutput {
  private final PrintStream out;
  private final PrintStream err;

  public PlainAttachOutput() {
    this(System.out, System.err);
  }

  public PlainAttachOutput(PrintStream out, PrintStream err) {
    this.out = out;
    this.err = err;
  }

  @Override
  public void attachStarted(String pid, String command, String agentArgs) {
    out.println("\n--- Attaching ---");
    out.println("\tPID "+ pid);
    out.println("\tCommand "+ command);
  }

  @Override
  public void finished() {
    out.flush();
    err.flush();
  }

  @Override
  public void attachFinished() {}

  @Override
  public void error(Exception e) {
    e.printStackTrace(err);
  }

  @Override
  public void warn(String message) {}
}
