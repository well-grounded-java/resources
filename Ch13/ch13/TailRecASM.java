package ch13;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TailRecASM {
    private final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    private String clazzName;

    public TailRecASM(String clazzName) {
        this.clazzName = clazzName;
    }

    public static void main(String[] args) {
        TailRecASM self = new TailRecASM("TailRecFactorial");
        try {
            self.writeClazz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeClazz() throws IOException {
        byte[] out = serializeToBytes(clazzName);
        Path newClazz = Paths.get(clazzName+".class");
        Files.write(newClazz, cw.toByteArray());
    }

    byte[] serializeToBytes(String outputClazzName) {
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, outputClazzName,
                null, "java/lang/Object", null);
        addStandardConstructor();
        addTailrecFactorial();
        addHelpFact();
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

//       0: lload_0
//       1: lconst_0
//       2: lcmp
//       3: ifgt         8
//       6: lconst_1
//       7: lreturn
//       8: lload_0
//       9: lconst_1
//      10: invokestatic  #49                 // Method helpFact:(JJ)J
//      13: lreturn
    void addTailrecFactorial() {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "tailrecFactorial", "(J)J", null, null);
        mv.visitCode();
        Label mainPath = new Label();
        mv.visitVarInsn(LLOAD, 0);
        mv.visitInsn(LCONST_0);
        mv.visitInsn(LCMP);
        mv.visitJumpInsn(IFGT, mainPath);
        mv.visitInsn(LCONST_1);
        mv.visitInsn(LRETURN);
        mv.visitLabel(mainPath);
        mv.visitVarInsn(LLOAD, 0);
        mv.visitInsn(LCONST_1);
        mv.visitMethodInsn(INVOKESTATIC, clazzName, "helpFact", "(JJ)J", false);
        mv.visitInsn(LRETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

//       0: lload_0
//       1: lconst_0
//       2: lcmp
//       3: ifne          8
//       6: lload_2
//       7: lreturn
//       8: lload_0
//       9: lconst_1
//      10: lsub
//      11: lload_0
//      12: lload_2
//      13: lmul
//      14: lstore_2
//      15: lstore_0
//      16: goto          0
    void addHelpFact() {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "helpFact", "(JJ)J", null, null);
        mv.visitCode();
        Label recurPoint = new Label();
        Label mainPath = new Label();
        mv.visitLabel(recurPoint);
        mv.visitVarInsn(LLOAD, 0);
        mv.visitInsn(LCONST_0);
        mv.visitInsn(LCMP);
        mv.visitJumpInsn(IFNE, mainPath);
        mv.visitVarInsn(LLOAD, 2);
        mv.visitInsn(LRETURN);
        mv.visitLabel(mainPath);
        mv.visitVarInsn(LLOAD, 0);
        mv.visitInsn(LCONST_1);
        mv.visitInsn(LSUB);
        mv.visitVarInsn(LLOAD, 0);
        mv.visitVarInsn(LLOAD, 2);
        mv.visitInsn(LMUL);
        mv.visitVarInsn(LSTORE, 2);
        mv.visitVarInsn(LSTORE, 0);
        mv.visitJumpInsn(GOTO, recurPoint);
//        mv.visitMethodInsn(INVOKESTATIC, clazzName, "helpFact", "(JJ)J", false);
//        mv.visitInsn(LRETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

//       0: aload_0
//       1: iconst_0
//       2: aaload
//       3: invokestatic  #2                  // Method java/lang/Long.parseLong:(Ljava/lang/String;)J
//       6: lstore_1
//       7: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
//      10: lload_1
//      11: invokestatic  #4                  // Method tailrecFactorial:(J)J
//      14: invokevirtual #5                  // Method java/io/PrintStream.println:(J)V
//      17: return
    void addMainMethod() {
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitInsn(ICONST_0);
        mv.visitInsn(AALOAD);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "parseLong", "(Ljava/lang/String;)J", false);
        mv.visitVarInsn(LSTORE, 1);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, clazzName, "tailrecFactorial", "(J)J", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
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
