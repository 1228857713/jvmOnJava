package com.wangzhen.jvm.instructions;



import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.instructions.comparisons.dcmp.DCMPG;
import com.wangzhen.jvm.instructions.comparisons.dcmp.DCMPL;
import com.wangzhen.jvm.instructions.comparisons.fcmp.FCMPG;
import com.wangzhen.jvm.instructions.comparisons.fcmp.FCMPL;
import com.wangzhen.jvm.instructions.comparisons.ifacmp.IF_ACMPEQ;
import com.wangzhen.jvm.instructions.comparisons.ifacmp.IF_ACMPNE;
import com.wangzhen.jvm.instructions.comparisons.ifcond.*;
import com.wangzhen.jvm.instructions.comparisons.ificmp.*;
import com.wangzhen.jvm.instructions.comparisons.lcmp.LCMP;
import com.wangzhen.jvm.instructions.constants.ACONST_NULL;
import com.wangzhen.jvm.instructions.constants.BIPUSH;
import com.wangzhen.jvm.instructions.constants.NOP;
import com.wangzhen.jvm.instructions.constants.SIPUSH;
import com.wangzhen.jvm.instructions.constants.*;
import com.wangzhen.jvm.instructions.control.*;
import com.wangzhen.jvm.instructions.conversions.d2x.D2F;
import com.wangzhen.jvm.instructions.conversions.d2x.D2I;
import com.wangzhen.jvm.instructions.conversions.d2x.D2L;
import com.wangzhen.jvm.instructions.conversions.f2x.F2D;
import com.wangzhen.jvm.instructions.conversions.f2x.F2I;
import com.wangzhen.jvm.instructions.conversions.f2x.F2L;
import com.wangzhen.jvm.instructions.conversions.i2x.*;
import com.wangzhen.jvm.instructions.conversions.l2x.L2D;
import com.wangzhen.jvm.instructions.conversions.l2x.L2F;
import com.wangzhen.jvm.instructions.conversions.l2x.L2I;
import com.wangzhen.jvm.instructions.extend.GOTO_W;
import com.wangzhen.jvm.instructions.extend.IFNONNULL;
import com.wangzhen.jvm.instructions.extend.IFNULL;
import com.wangzhen.jvm.instructions.extend.WIDE;
import com.wangzhen.jvm.instructions.loads.loadDouble.*;
import com.wangzhen.jvm.instructions.loads.loadFloat.*;
import com.wangzhen.jvm.instructions.loads.loadInt.ILOAD;
import com.wangzhen.jvm.instructions.loads.loadInt.*;
import com.wangzhen.jvm.instructions.loads.loadlong.*;
import com.wangzhen.jvm.instructions.loads.loadref.*;
import com.wangzhen.jvm.instructions.loads.loadxarr.*;
import com.wangzhen.jvm.instructions.math.add.DADD;
import com.wangzhen.jvm.instructions.math.add.FADD;
import com.wangzhen.jvm.instructions.math.add.IADD;
import com.wangzhen.jvm.instructions.math.add.LADD;
import com.wangzhen.jvm.instructions.math.and.IAND;
import com.wangzhen.jvm.instructions.math.and.LAND;
import com.wangzhen.jvm.instructions.math.div.DDIV;
import com.wangzhen.jvm.instructions.math.div.FDIV;
import com.wangzhen.jvm.instructions.math.div.IDIV;
import com.wangzhen.jvm.instructions.math.div.LDIV;
import com.wangzhen.jvm.instructions.math.iinc.IINC;
import com.wangzhen.jvm.instructions.math.mul.DMUL;
import com.wangzhen.jvm.instructions.math.mul.FMUL;
import com.wangzhen.jvm.instructions.math.mul.IMUL;
import com.wangzhen.jvm.instructions.math.mul.LMUL;
import com.wangzhen.jvm.instructions.math.neg.DNEG;
import com.wangzhen.jvm.instructions.math.neg.FNEG;
import com.wangzhen.jvm.instructions.math.neg.INEG;
import com.wangzhen.jvm.instructions.math.neg.LNEG;
import com.wangzhen.jvm.instructions.math.or.IOR;
import com.wangzhen.jvm.instructions.math.or.LOR;
import com.wangzhen.jvm.instructions.math.rem.DREM;
import com.wangzhen.jvm.instructions.math.rem.FREM;
import com.wangzhen.jvm.instructions.math.rem.IREM;
import com.wangzhen.jvm.instructions.math.rem.LREM;
import com.wangzhen.jvm.instructions.math.sh.*;
import com.wangzhen.jvm.instructions.math.sub.DSUB;
import com.wangzhen.jvm.instructions.math.sub.FSUB;
import com.wangzhen.jvm.instructions.math.sub.ISUB;
import com.wangzhen.jvm.instructions.math.sub.LSUB;
import com.wangzhen.jvm.instructions.math.xor.IXOR;
import com.wangzhen.jvm.instructions.math.xor.LXOR;
import com.wangzhen.jvm.instructions.references.*;
import com.wangzhen.jvm.instructions.stack.dup.*;
import com.wangzhen.jvm.instructions.stack.pop.POP;
import com.wangzhen.jvm.instructions.stack.pop.POP2;
import com.wangzhen.jvm.instructions.stack.swap.SWAP;
import com.wangzhen.jvm.instructions.stores.storedouble.*;
import com.wangzhen.jvm.instructions.stores.storefloat.*;
import com.wangzhen.jvm.instructions.stores.storeint.*;
import com.wangzhen.jvm.instructions.stores.storelong.*;
import com.wangzhen.jvm.instructions.stores.storeref.*;
import com.wangzhen.jvm.instructions.stores.storexarr.*;


/**
 *
 * Author: wangzhen
 * Time: 2019/09/29
 * Desc: Java 虚拟机规范已经定义了 205 条指令，操作码分别是 0（0x00）到 202（0xCA）、254（0xFE）和 255（0xFF）。
 *       这 205 条指令构成了 Java 虚拟机的指令集（instruction set）。
 */
public class InstructionFactory {
    static NOP nop = new NOP();
    static ACONST_NULL aconst_null = new ACONST_NULL();
    static ICONST_M1 iconst_m1 = new ICONST_M1();
    static ICONST_0 iconst_0 = new ICONST_0();
    static ICONST_1 iconst_1 = new ICONST_1();
    static ICONST_2 iconst_2 = new ICONST_2();
    static ICONST_3 iconst_3 = new ICONST_3();
    static ICONST_4 iconst_4 = new ICONST_4();
    static ICONST_5 iconst_5 = new ICONST_5();
    static LCONST_0 lconst_0 = new LCONST_0();
    static LCONST_1 lconst_1 = new LCONST_1();
    static FCONST_0 fconst_0 = new FCONST_0();
    static FCONST_1 fconst_1 = new FCONST_1();
    static FCONST_2 fconst_2 = new FCONST_2();
    static DCONST_0 dconst_0 = new DCONST_0();
    static DCONST_1 dconst_1 = new DCONST_1();
    static ILOAD_0 iload_0 = new ILOAD_0();
    static ILOAD_1 iload_1 = new ILOAD_1();
    static ILOAD_2 iload_2 = new ILOAD_2();
    static ILOAD_3 iload_3 = new ILOAD_3();
    static LLOAD_0 lload_0 = new LLOAD_0();
    static LLOAD_1 lload_1 = new LLOAD_1();
    static LLOAD_2 lload_2 = new LLOAD_2();
    static LLOAD_3 lload_3 = new LLOAD_3();
    static FLOAD_0 fload_0 = new FLOAD_0();
    static FLOAD_1 fload_1 = new FLOAD_1();
    static FLOAD_2 fload_2 = new FLOAD_2();
    static FLOAD_3 fload_3 = new FLOAD_3();
    static DLOAD_0 dload_0 = new DLOAD_0();
    static DLOAD_1 dload_1 = new DLOAD_1();
    static DLOAD_2 dload_2 = new DLOAD_2();
    static DLOAD_3 dload_3 = new DLOAD_3();
    static ALOAD_0 aload_0 = new ALOAD_0();
    static ALOAD_1 aload_1 = new ALOAD_1();
    static ALOAD_2 aload_2 = new ALOAD_2();
    static ALOAD_3 aload_3 = new ALOAD_3();
    static ISTORE_0 istore_0 = new ISTORE_0();
    static ISTORE_1 istore_1 = new ISTORE_1();
    static ISTORE_2 istore_2 = new ISTORE_2();
    static ISTORE_3 istore_3 = new ISTORE_3();
    static LSTORE_0 lstore_0 = new LSTORE_0();
    static LSTORE_1 lstore_1 = new LSTORE_1();
    static LSTORE_2 lstore_2 = new LSTORE_2();
    static LSTORE_3 lstore_3 = new LSTORE_3();
    static FSTORE_0 fstore_0 = new FSTORE_0();
    static FSTORE_1 fstore_1 = new FSTORE_1();
    static FSTORE_2 fstore_2 = new FSTORE_2();
    static FSTORE_3 fstore_3 = new FSTORE_3();
    static DSTORE_0 dstore_0 = new DSTORE_0();
    static DSTORE_1 dstore_1 = new DSTORE_1();
    static DSTORE_2 dstore_2 = new DSTORE_2();
    static DSTORE_3 dstore_3 = new DSTORE_3();
    static ASTORE_0 astore_0 = new ASTORE_0();
    static ASTORE_1 astore_1 = new ASTORE_1();
    static ASTORE_2 astore_2 = new ASTORE_2();
    static ASTORE_3 astore_3 = new ASTORE_3();
    static IADD iadd = new IADD();
    static LCMP lcmp =new LCMP();
    static FCMPG fcmpg = new FCMPG();
    static FCMPL fcmpl = new FCMPL();
    static DCMPG dcmpg = new DCMPG();
    static DCMPL dcmpl = new DCMPL();
    static IFEQ ifeq =new IFEQ();
    static IFNE ifne =new IFNE();
    static IFLT iflt =new IFLT();
    static IFGE ifge = new IFGE();
    static IFGT ifgt = new IFGT();
    static IFLE ifle = new IFLE();
    static IF_ICMPEQ if_icmpeq =new  IF_ICMPEQ();
    static IF_ICMPNE if_icmpne = new IF_ICMPNE();
    static IF_ICMPLT if_icmplt = new IF_ICMPLT();
    static IF_ICMPGE if_icmpge = new IF_ICMPGE();
    static IF_ICMPGT if_icmpgt = new IF_ICMPGT();
    static IF_ICMPLE if_icmple = new IF_ICMPLE();
    static IF_ACMPEQ if_acmpeq = new IF_ACMPEQ();
    static IF_ACMPNE if_acmpne = new IF_ACMPNE();
    static RETURN _return = new RETURN();
    static DUP dup = new DUP();
    static IASTORE iastore = new IASTORE();
    static BIPUSH bipush = new BIPUSH();
    static SIPUSH sipush = new SIPUSH();
    static LDC ldc = new LDC();
    static LDC_W ldc_w = new LDC_W();
    static  LDC2_W ldc2_w = new LDC2_W();
    static ILOAD iload = new ILOAD();
    static LLOAD lload = new LLOAD();
    static FLOAD fload = new FLOAD();
    static DLOAD dload = new DLOAD();
    static ALOAD aload = new ALOAD();
    static AALOAD aaload = new AALOAD();
    static BALOAD baload = new BALOAD();
    static CALOAD caload = new CALOAD();
    static DALOAD daload = new DALOAD();
    static FALOAD faload = new FALOAD();
    static IALOAD iaload = new IALOAD();
    static LALOAD laload = new LALOAD();
    static SALOAD saload = new SALOAD();
    static ISTORE istore = new ISTORE();
    static LSTORE lstore = new LSTORE();
    static FSTORE fstore = new FSTORE();
    static DSTORE dstore = new DSTORE();
    static ASTORE astore = new ASTORE();

    static LASTORE lastore = new LASTORE();
    static FASTORE fastore = new FASTORE();
    static DASTORE dastore = new DASTORE();
    static AASTORE aastore = new AASTORE();
    static BASTORE bastore = new BASTORE();
    static CASTORE castore = new CASTORE();
    static SASTORE sastore = new SASTORE();
    static POP pop = new POP();
    static POP2 pop2 =new POP2();
    static DUP_X1 dup_x1 = new DUP_X1();
    static DUP_X2 dup_x2 = new DUP_X2();
    static DUP2 dup2 = new DUP2();
    static DUP2_X1 dup2_x1 = new DUP2_X1();
    static DUP2_X2 dup2_x2 = new DUP2_X2();
    static SWAP swap = new SWAP();
    static LADD ladd = new LADD();
    static FADD fadd = new FADD();
    static DADD dadd = new DADD();
    static ISUB isub = new ISUB();
    static LSUB lsub = new LSUB();
    static FSUB fsub = new FSUB();
    static DSUB dsub = new DSUB();
    static IMUL imul = new IMUL();
    static LMUL lmul = new LMUL();
    static FMUL fmul = new FMUL();
    static DMUL dmul = new DMUL();
    static IDIV idiv = new IDIV();
    static LDIV ldiv = new LDIV();
    static FDIV fdiv = new FDIV();
    static DDIV ddiv = new DDIV();
    static IREM irem = new IREM();
    static LREM lrem = new LREM();
    static FREM frem = new FREM();
    static DREM drem = new DREM();
    static INEG ineg = new INEG();
    static LNEG lneg = new LNEG();
    static FNEG fneg = new FNEG();
    static DNEG dneg = new DNEG();
    static IAND iand = new IAND();
    static LAND land = new LAND();
    static IOR ior = new IOR();
    static LOR lor = new LOR();
    static IXOR ixor = new IXOR();
    static LXOR lxor = new LXOR();
    static IINC iinc = new IINC();
    static I2L i2l = new I2L();
    static I2F i2f = new I2F();
    static I2D i2d = new I2D();
    static L2I l2i = new L2I();
    static L2F l2f = new L2F();
    static L2D l2d = new L2D();
    static F2I f2i = new F2I();
    static F2L f2l = new F2L();
    static F2D f2d = new F2D();
    static D2I d2i = new D2I();
    static D2L d2l = new D2L();
    static D2F d2f = new D2F();
    static I2B i2b = new I2B();
    static I2C i2c = new I2C();
    static I2S i2s = new I2S();
    static ISHL ishl = new ISHL();
    static ISHR ishr = new ISHR();
    static IUSHR iushr = new IUSHR();
    static LSHL lshl = new LSHL();
    static LSHR lshr = new LSHR();
    static LUSHR lushr = new LUSHR();
    static ARETURN areturn = new ARETURN();
    static DRETURN dreturn = new DRETURN();
    static FRETURN freturn = new FRETURN();
    static IRETURN ireturn = new IRETURN();
    static LOOKUP_SWITCH lookup_switch = new LOOKUP_SWITCH();
    static LRETURN lreturn = new LRETURN();
    static TABLE_SWITCH table_switch = new TABLE_SWITCH();
    static GOTO _goto = new GOTO();
    static ARRAY_LENGTH array_length = new ARRAY_LENGTH();
    static ATHROW athrow = new ATHROW();
    static CHECK_CAST check_cast = new CHECK_CAST();
    static INSTANCE_OF instance_of = new INSTANCE_OF();
    static INVOKE_INTERFACE invoke_interface = new INVOKE_INTERFACE();
    static  INVOKE_NATIVE invoke_native = new INVOKE_NATIVE();
    static MULTI_ANEW_ARRAY multi_anew_array = new MULTI_ANEW_ARRAY();
    static PUT_FIELD put_field = new PUT_FIELD();
    static INVOKE_DYNAMIC invoke_dynamic = new INVOKE_DYNAMIC();
    static GET_FIELD get_field = new GET_FIELD();
    static GET_STATIC get_static = new GET_STATIC();
    static PUT_STATIC put_static = new PUT_STATIC();
    static INVOKE_SPECIAL invoke_special = new INVOKE_SPECIAL();
    static INVOKE_STATIC invoke_static = new INVOKE_STATIC();
    static  INVOKE_VIRTUAL invoke_virtual = new INVOKE_VIRTUAL();
    static ANEW_ARRAY anew_array = new ANEW_ARRAY();
    static NEW _new = new NEW();
    static NEW_ARRAY new_array = new NEW_ARRAY();
    static GOTO_W goto_w = new GOTO_W();
    static IFNONNULL ifnonnull = new IFNONNULL();
    static IFNULL ifnull = new IFNULL();
    static WIDE wide = new WIDE();








    public static Instruction createInstruction(int opCode) {
        switch (opCode) {
            case 0x00:
                return nop;
            case 0x01:
                return aconst_null;
            case 0x02:
                return iconst_m1;
            case 0x03:
                return iconst_0;
            case 0x04:
                return iconst_1;
            case 0x05:
                return iconst_2;
            case 0x06:
                return iconst_3;
            case 0x07:
                return iconst_4;
            case 0x08:
                return iconst_5;
            case 0x09:
                return lconst_0;
            case 0x0a:
                return lconst_1;
            case 0x0b:
                return fconst_0;
            case 0x0c:
                return fconst_1;
            case 0x0d:
                return fconst_2;
            case 0x0e:
                return dconst_0;
            case 0x0f:
                return dconst_1;
            case 0x10:
                return bipush;
            case 0x11:
                return sipush;
            case 0x12:
                return ldc;
            case 0x13:
                return ldc_w;
            case 0x14:
                return ldc2_w;
            case 0x15:
                return iload;
            case 0x16:
                return lload;
            case 0x17:
                return fload;
            case 0x18:
                return dload;
            case 0x19:
                return aload;
            case 0x1a:
                return iload_0;
            case 0x1b:
                return iload_1;
            case 0x1c:
                return iload_2;
            case 0x1d:
                return iload_3;
            case 0x1e:
                return lload_0;
            case 0x1f:
                return lload_1;
            case 0x20:
                return lload_2;
            case 0x21:
                return lload_3;
            case 0x22:
                return fload_0;
            case 0x23:
                return fload_1;
            case 0x24:
                return fload_2;
            case 0x25:
                return fload_3;
            case 0x26:
                return dload_0;
            case 0x27:
                return dload_1;
            case 0x28:
                return dload_2;
            case 0x29:
                return dload_3;
            case 0x2a:
                return aload_0;
            case 0x2b:
                return aload_1;
            case 0x2c:
                return aload_2;
            case 0x2d:
                return aload_3;
            case 0x2e:
                return iaload;
            case 0x2f:
                return laload;
            case 0x30:
                return faload;
            case 0x31:
                return daload;
            case 0x32:
                return aaload;
            case 0x33:
                return baload;
            case 0x34:
                return caload;
            case 0x35:
                return saload;
            case 0x36:
                return istore;
            case 0x37:
                return lstore;
            case 0x38:
                return fstore;
            case 0x39:
                return dstore;
            case 0x3a:
                return astore;
            case 0x3b:
                return istore_0;
            case 0x3c:
                return istore_1;
            case 0x3d:
                return istore_2;
            case 0x3e:
                return istore_3;
            case 0x3f:
                return lstore_0;
            case 0x40:
                return lstore_1;
            case 0x41:
                return lstore_2;
            case 0x42:
                return lstore_3;
            case 0x43:
                return fstore_0;
            case 0x44:
                return fstore_1;
            case 0x45:
                return fstore_2;
            case 0x46:
                return fstore_3;
            case 0x47:
                return dstore_0;
            case 0x48:
                return dstore_1;
            case 0x49:
                return dstore_2;
            case 0x4a:
                return dstore_3;
            case 0x4b:
                return astore_0;
            case 0x4c:
                return astore_1;
            case 0x4d:
                return astore_2;
            case 0x4e:
                return astore_3;
            case 0x4f:
                 return iastore;
            case 0x50:
                return lastore;
            case 0x51:
                return fastore;
            case 0x52:
                return dastore;
            case 0x53:
                return aastore;
            case 0x54:
                return bastore;
            case 0x55:
                return castore;
            case 0x56:
                return sastore;
            case 0x57:
                return pop;
            case 0x58:
                return pop2;
            case 0x59:
                return dup;
            case 0x5a:
                return dup_x1;
            case 0x5b:
                return dup_x2;
            case 0x5c:
                return dup2;
            case 0x5d:
                return dup2_x1;
            case 0x5e:
                return dup2_x2;
            case 0x5f:
                return swap;
            case 0x60:
                return iadd;
            case 0x61:
                return ladd;
            case 0x62:
                return fadd;
            case 0x63:
                return dadd;
            case 0x64:
                return isub;
            case 0x65:
                return lsub;
            case 0x66:
                return fsub;
            case 0x67:
                return dsub;
            case 0x68:
                return imul;
            case 0x69:
                return lmul;
            case 0x6a:
                return fmul;
            case 0x6b:
                return dmul;
            case 0x6c:
                return idiv;
            case 0x6d:
                return ldiv;
            case 0x6e:
                return fdiv;
            case 0x6f:
                return ddiv;
            case 0x70:
                return irem;
            case 0x71:
                return lrem;
            case 0x72:
                return frem;
            case 0x73:
                return drem;
            case 0x74:
                return ineg;
            case 0x75:
                return lneg;
            case 0x76:
                return fneg;
            case 0x77:
                return dneg;
            case 0x78:
                return ishl;
            case 0x79:
                return lshl;
            case 0x7a:
                return ishr;
            case 0x7b:
                return lshr;
            case 0x7c:
                return iushr;
            case 0x7d:
                return lushr;
            case 0x7e:
                return iand;
            case 0x7f:
                return land;
            case 0x80:
                return ior;
            case 0x81:
                return lor;
            case 0x82:
                return ixor;
            case 0x83:
                return lxor;
            case 0x84:
                return iinc;
            case 0x85:
                return i2l;
            case 0x86:
                return i2f;
            case 0x87:
                return i2d;
            case 0x88:
                return l2i;
            case 0x89:
                return l2f;
            case 0x8a:
                return l2d;
            case 0x8b:
                return f2i;
            case 0x8c:
                return f2l;
            case 0x8d:
                return f2d;
            case 0x8e:
                return d2i;
            case 0x8f:
                return d2l;
            case 0x90:
                return d2f;
            case 0x91:
                return i2b;
            case 0x92:
                return i2c;
            case 0x93:
                return i2s;
            case 0x94:
                return lcmp;
            case 0x95:
                return fcmpl;
            case 0x96:
                return fcmpg;
            case 0x97:
                return dcmpl;
            case 0x98:
                return dcmpg;
            case 0x99:
                return ifeq;
            case 0x9a:
                return ifne;
            case 0x9b:
                return iflt;
            case 0x9c:
                return ifge;
            case 0x9d:
                return ifgt;
            case 0x9e:
                return ifle;
            case 0x9f:
                return if_icmpeq;
            case 0xa0:
                return if_icmpne;
            case 0xa1:
                return if_icmplt;
            case 0xa2:
                return if_icmpge;
            case 0xa3:
                return if_icmpgt;
            case 0xa4:
                return if_icmple;
            case 0xa5:
                return if_acmpeq;
            case 0xa6:
                return if_acmpne;
            case 0xa7:
                return _goto;
            // case 0xa8:
            // 	return new JSR();
            // case 0xa9:
            // 	return new RET();
            case 0xaa:
                return table_switch;
            case 0xab:
                return lookup_switch;
            case 0xac:
                return ireturn;
            case 0xad:
                return lreturn;
            case 0xae:
                return freturn;
            case 0xaf:
                return dreturn;
            case 0xb0:
                return areturn;
            case 0xb1:
                return _return;
            case 0xb2:
                return get_static;
            case 0xb3:
                return put_static;
            case 0xb4:
                return get_field;
            case 0xb5:
                return put_field;
            case 0xb6:
                return invoke_virtual;
            case 0xb7:
                return invoke_special;
            case 0xb8:
                return invoke_static;
            case 0xb9:
                return invoke_interface;
             case 0xba:
             	return invoke_dynamic;
            case 0xbb:
                return _new;
            case 0xbc:
                return new_array;
            case 0xbd:
                return anew_array;
            case 0xbe:
                return array_length;
            case 0xbf:
                return athrow;
            case 0xc0:
                return new CHECK_CAST();
            case 0xc1:
                return new INSTANCE_OF();
            // case 0xc2:
            // 	return monitorenter;
            // case 0xc3:
            // 	return monitorexit;
            case 0xc4:
                return wide;
            case 0xc5:
                return multi_anew_array;
            case 0xc6:
                return ifnull;
            case 0xc7:
                return ifnonnull;
            case 0xc8:
                return goto_w;
//             case 0xc9:
//             	return new JSR_W();
//             case 0xca:
//                 return breakpoint;
            case 0xfe:
                return invoke_native;
//             case 0xff:
//                 return impdep2;
            default:
                throw new RuntimeException("Unsupported opcode: " + opCode);
        }
    }
}
