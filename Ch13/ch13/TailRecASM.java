package ch13;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TailRecASM {
    private final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

    public static void main(String[] args) {
        TailRecASM self = new TailRecASM();
        try {
            self.writeClazz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeClazz() throws IOException {
        String cName = "TailRecFactorial";
        byte[] out = serializeToBytes(cName);
        Path newClazz = Paths.get(cName+".class");
        Files.write(newClazz, cw.toByteArray());
    }

    byte[] serializeToBytes(String outputClazzName) {
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, outputClazzName,
                null, "java/lang/Object", null);
        addStandardConstructor();
        addMainMethod();
        cw.visitEnd();
        return cw.toByteArray();
    }

    void addStandardConstructor() {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    void addMainMethod() {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello World!");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

//    void transform(String fName) {
//        try (InputStream in = Files.newInputStream(Paths.get(fName))) {
//            ClassReader classReader = new ClassReader(in);
//            ClassWriter writer = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
//
//            ClassVisitor unsynchronizer = new UnsynchronizingClassVisitor(writer);
//            classReader.accept(unsynchronizer, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
//
//            Path newClazz = Paths.get(transformName(fName));
//            Files.write(newClazz, writer.toByteArray());
//        } catch (Exception ex) {
//            System.err.println("Exception whilst reading class: " + fName);
//            ex.printStackTrace(System.err);
//        }
//    }
}
