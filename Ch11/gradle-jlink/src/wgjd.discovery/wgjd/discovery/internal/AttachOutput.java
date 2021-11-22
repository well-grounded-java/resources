package wgjd.discovery.internal;

public interface AttachOutput {
  void attachStarted(String pid, String command, String agentArgs);

  void attachFinished();

  void finished();

  void error(Exception e);

  void warn(String message);
}
