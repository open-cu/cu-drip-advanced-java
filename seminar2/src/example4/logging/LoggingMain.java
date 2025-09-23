package logging;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class LoggingMain {

    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of("../example1/Hello.class"));
        ClassReader cr = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(cr, 0);
        cr.accept(new AddLoggerClassVisitor(writer), 0);
        byte[] modifiedClass = writer.toByteArray();

        // 5. Записываем в файл (перезапишет Hello.class)
        try (FileOutputStream fos = new FileOutputStream("../example1/HelloLog.class")) {
            fos.write(modifiedClass);
        }

        System.out.println("Класс Hello модифицирован. Теперь он логирует вход в методы.");
    }

    static class AddLoggerClassVisitor extends ClassVisitor {
        public AddLoggerClassVisitor(ClassVisitor cv) { super(Opcodes.ASM9, cv); }
    
        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String sig, String[] ex) {
            MethodVisitor mv = super.visitMethod(access, name, desc, sig, ex);
            return new AdviceAdapter(Opcodes.ASM9, mv, access, name, desc) {
                @Override
                protected void onMethodEnter() {
                    // вставить System.out.println("Enter: " + name);
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn("Enter method: " + name);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }
            };
        }
    }

}
