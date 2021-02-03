package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantPool {
    public int constantPoolCount;
    public int realConstantPoolCount;
    public ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader classReader) {
        // 读取 2个字节的 常量池大小数量
        constantPoolCount = classReader.readNByteToInt(2);
        constantInfos = new ConstantInfo[constantPoolCount];
        for (int i =1;i< constantPoolCount;i++){
            constantInfos[i] = createConstantInfo(classReader);
            realConstantPoolCount++;
            // Long型 和Double 型占用两个 常量池大小
            if ((constantInfos[i] instanceof ConstantLongInfo) || (constantInfos[i] instanceof ConstantDoubleInfo)) {
                i++;
            }
        }
    }

    public ConstantInfo createConstantInfo(ClassReader classReader){
        // 先读取常量池的tag标记其是哪一次信息
        int tag = ByteUtils.bytesToInt(classReader.readUint1());
        switch (tag){
            case ConstantInfo.CONSTANT_utf8_info :
                return new ConstantUtf8Info(classReader);
            case ConstantInfo.CONSTANT_Integer_info:
                return new ConstantIntegerInfo(classReader);
            case ConstantInfo.CONSTANT_Float_info:
                return new ConstantFloatInfo(classReader);
            case ConstantInfo.CONSTANT_Long_info:
                return new ConstantLongInfo(classReader);
            case ConstantInfo.CONSTANT_Double_info:
                return new ConstantDoubleInfo(classReader);
            case ConstantInfo.CONSTANT_Class_info:
                return new ConstantClassInfo(this,classReader);
            case ConstantInfo.CONSTANT_String_info:
                return new ConstantStringInfo(this,classReader);
            case ConstantInfo.CONSTANT_Fieldref_info:
                return new ConstantFieldRefInfo(this,tag,classReader);
            case ConstantInfo.CONSTANT_Methodref_info:
                return new ConstantMethodRefInfo(this,tag,classReader);
            case ConstantInfo.CONSTANT_InterfaceMethodref_info:
                return new ConstantInterfaceMethodRefInfo(this,tag,classReader);
            case ConstantInfo.CONSTANT_NameAndType_info:
                return new ConstantNameAndTypeInfo(this,classReader);
            case ConstantInfo.CONSTANT_MethodHandle_info:
                return new ConstantMethodHandleInfo(classReader);
            case ConstantInfo.CONSTANT_MethodType_info:
                return new ConstantMethodTypeInfo(classReader);
            case ConstantInfo.CONSTANT_InvokeDynamic_info:
                return new ConstantInvokeDynamicInfo(classReader);
            default:
               throw new RuntimeException("java.lang.ClassFormatError: constant pool tag!");
               // return null;







        }

    }

    //按索引查找常量,如果没有的话,直接抛异常;
    private ConstantInfo getConstantInfo(int index) {
        if (0 < index && index < constantPoolCount) {
            ConstantInfo info = constantInfos[index] ;
            if (info != null) {
                return info;
            }
        }
        throw new NullPointerException("Invalid constant pool index!");
    }


    // 获取常量池中的 静态字符
    public String getConstantPoolUtf8Value(int index){
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) constantInfos[index];
        return  constantUtf8Info.getValue();
    }

    //常量池查找字段或方法的名字和描述符
    public String getName(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getConstantPoolUtf8Value(info.functionNameIndex);
    }

    //常量池查找字段或方法的描述符,描述符其实就是由其对应的类型名字对应而成;
    public String getType(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getConstantPoolUtf8Value(info.descriptionIndex);
    }

    public String[] getNameAndType(int index) {
        String[] str = new String[2];
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        str[0] = getConstantPoolUtf8Value(info.functionNameIndex);
        str[1] = getConstantPoolUtf8Value(info.descriptionIndex);
        return str;
    }

    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo) getConstantInfo(index);
        return getConstantPoolUtf8Value(info.nameIndex);
    }


    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public int getRealConstantPoolCount() {
        return realConstantPoolCount;
    }

    public void setRealConstantPoolCount(int realConstantPoolCount) {
        this.realConstantPoolCount = realConstantPoolCount;
    }

    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public void setConstantInfos(ConstantInfo[] constantInfos) {
        this.constantInfos = constantInfos;
    }
}
