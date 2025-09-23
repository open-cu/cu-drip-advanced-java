package invoke;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.nio.file.Files;
import java.nio.file.Path;

public class FindInvokes {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of("../example2/LambdaExample.class"));
        ClassReader cr = new ClassReader(bytes);
        cr.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String sig, String[] ex) {
                return new MethodVisitor(Opcodes.ASM9) {
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                        if (opcode == Opcodes.INVOKEVIRTUAL) {
                            System.out.println("invokevirtual: " + owner + "." + name + descriptor);
                        } else if (opcode == Opcodes.INVOKEDYNAMIC) {
                            System.out.println("invokedynamic instruction found: " + name + " " + descriptor);
                        } else {
                            System.out.println("other invoke: opcode=" + opcode + " " + owner + "." + name);
                        }
                        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                    }
                };
            }
        }, 0);
    }
}
