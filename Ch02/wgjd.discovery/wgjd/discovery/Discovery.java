package wgjd.discovery;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;
import java.util.Set;

import wgjd.discovery.internal.PlainAttachOutput;

public class Discovery {
  private static final Set<String> PROCESS_SKIP_TERMS =
      Set.of("sun.tools.jconsole.JConsole", "gradle");

  public static void main(String[] args) {
    var output = new PlainAttachOutput();

    try {
      final var vmConsumer = new VMIntrospector();
      System.out.println("Java processes:");
      System.out.println("PID\tDisplay Name\tVM Version\tAttachable");

      final List<VirtualMachineDescriptor> vmds = VirtualMachine.list();
      for (var vmd : vmds) {
        if (!skip(vmd)) {
          vmConsumer.accept(vmd);
        }
      }
    } finally {
      output.finished();
    }
  }

  private static boolean skip(VirtualMachineDescriptor vmd) {
    final var displayName = vmd.displayName();
    for (var term : PROCESS_SKIP_TERMS) {
      if (displayName.contains(term)) {
        return true;
      }
    }
    return // REVIEW not sure if we can skip all of these
    displayName.isEmpty();
  }
}
