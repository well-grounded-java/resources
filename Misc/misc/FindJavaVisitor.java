package misc;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FindJavaVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (file != null && attrs != null) {
            if (file.getFileName().toString().endsWith(".java")) {
                System.out.println(file.getFileName());
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
