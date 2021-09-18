package misc;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.attribute.PosixFilePermission.GROUP_READ;

public class MiscFiles {

    public static void main(String[] args) {
        MiscFiles m = new MiscFiles();
//        m.run();
//        try {
//            m.run2();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        m.run3();
//        m.run4();
//        m.run5();
//        try {
//            m.run8();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        m.run10();
    }

    void run10() {
        Path p;
        try {
            p = Files.createTempFile("temp", null);
            System.out.println(p.getFileName());
            try (var output = Files.newOutputStream(p, DELETE_ON_CLOSE)) {
                // do work with the OutputStream
                // Temp file will be automatically cleaned up
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run9() {
        boolean shutdown = false;

        try {
            var watcher = FileSystems.getDefault().newWatchService();
            var dir = Path.of("/Users/ben");

            var registered = dir.register(watcher, ENTRY_MODIFY);

            while (!shutdown) {
                WatchKey key = null;
                try {
                    key = watcher.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event == null) {
                            continue;
                        }
                        if (event.kind() == ENTRY_MODIFY) {
                            System.out.println("Home dir changed!");
                        }
                    }
                    key.reset();
                } catch (InterruptedException e) {
                    // Log interruption
                    shutdown = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void a(int x) {
        if (x > 1) {
            Supplier<Integer> f = () -> x + 1;
            f.get();
        }
    }

    void run8() throws IOException {
        var startingDir = Path.of("/Users/ben/projects/openjdk/jdk");
        Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    void run7() {
        try {
            var dir = Path.of("/Users/ben/projects/openjdk/jdk/");
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java");
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path unpackJar(String zipFilePath) throws IOException {
        var tmpDir = Files.createTempDirectory(Path.of("/tmp"), "jar-extract");

        try (var zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            var entry = zipIn.getNextEntry();

            while (entry != null) {
                var newFile = tmpDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectory(newFile);
                } else {
                    Files.copy(zipIn, newFile);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }

        return tmpDir;
    }


    void run5() {
        try {
            var teamList = Path.of("/Users/ben/kanji.txt");
            PosixFileAttributes attrs =
                    Files.readAttributes(teamList, PosixFileAttributes.class);

            Set<PosixFilePermission> posixFilePermissions = attrs.permissions();

            var owner = attrs.owner().getName();
            var perms = PosixFilePermissions.toString(posixFilePermissions);
            System.out.format("%s %s%n", owner, perms);

            posixFilePermissions.add(GROUP_READ);
            Files.setPosixFilePermissions(teamList, posixFilePermissions);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    private void run6() {
        Path path = Path.of("/opt/maven");
        try {
            if (Files.isSymbolicLink(path)) {
                var contents = Files.readSymbolicLink(path);
                var target = path.toRealPath();
                System.out.println("Link contents: "+ contents);
                System.out.println("Link target: "+ target);
                var attrs = Files.readAttributes(target, BasicFileAttributes.class);
                System.out.println(attrs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run4() {
        var alice = Path.of("/Users/ben/whistle_come_to_you.txt");
        try {
            long count = Files.lines(alice)
                    .flatMap(l -> Stream.of(l.split("")))
                    .filter(s -> s.equals("a"))
                    .count();
            System.out.println("'a's seen: "+ count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run3() {
        try (var is = new FileInputStream("/Users/ben/whistle_come_to_you.txt")) {
            var buf = new byte[4096];
            int len, count = 0;
            while ((len = is.read(buf)) > 0) {
                for (var i=0; i < len; i = i + 1)
                    if (buf[i] == 97) {
                        count = count + 1;
                    }
            }
            System.out.println("'a's seen: "+ count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run2() throws URISyntaxException {
        var p = Path.of("/Users/ben/cluster.txt");
        var p2 = Path.of(new URI("file:///Users/ben/cluster.txt"));
        System.out.println(p2.equals(p));

        File f = p.toFile();
        System.out.println(f.isDirectory());
        Path p3 = f.toPath();
        System.out.println(p3.equals(p));
    }

    public void run() {
        var input = Path.of("input");
        var output = Path.of("output");

        try {
            Files.copy(input, output);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

}
