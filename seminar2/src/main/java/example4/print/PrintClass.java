package example4.print;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintClass {
    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of("../example1/Hello.class"));
        ClassReader cr = new ClassReader(bytes);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                System.out.println("Class: " + name + " extends " + superName);
            }
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println(" Method: " + name + " " + descriptor);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        cr.accept(cv, 0);
    }
}
