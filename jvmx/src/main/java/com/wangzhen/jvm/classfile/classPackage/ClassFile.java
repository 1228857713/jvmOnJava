package com.wangzhen.jvm.classfile.classPackage;


import com.wangzhen.jvm.attribute.AttributeInfo;
import com.wangzhen.jvm.attribute.SourceFileAttribute;
import com.wangzhen.jvm.classConstant.ConstantClassInfo;
import com.wangzhen.jvm.classConstant.ConstantPool;

import com.wangzhen.jvm.utils.ByteUtils;

import java.util.Arrays;

public class ClassFile {
    //魔数 0xcafebabe  u4
    String magic;
    // 次版本号 u2
    int minorVersion;
    // 主版本号 u2
    int majorVersion;
    // 常量池
    ConstantPool constantPool;
    // 访问标识 u2
    int accessFlags;
    // 类索引 u2
    int thisClassIndex;
    // 父类索引 u2
    int superClassIndex;

    // 本类实现的接口数量 u2
    int interfacesCount;
    // 实现的接口 存放在数组中
    int[] interfaces;

    // 字段表数量
    int fieldsCount;
    // 字段
    MemberInfo[] fields;

    // 方法数量
    int methodsCount;
    // 方法
    MemberInfo[] methods;

    // 属性数量
    int attributesCount;

    // 属性
    AttributeInfo[] attributes;


    public ClassFile(byte[] classData) {
        ClassReader classReader = new ClassReader(classData);
        read(classReader);

    }
    public void read(ClassReader classReader){
        // 读取魔数
        this.magic =  ByteUtils.bytesToHexString(classReader.readUint4());
       // 读取次版本号
        this.minorVersion = ByteUtils.bytesToInt(classReader.readUint2());
        // 读取主版本号
        this.majorVersion = ByteUtils.bytesToInt(classReader.readUint2());
        // 读取常量池
        constantPool = new ConstantPool(classReader);
        // 读取访问标识符
        this.accessFlags = ByteUtils.bytesToInt(classReader.readNByte(2));
        // 读取类索引
        this.thisClassIndex = ByteUtils.bytesToInt(classReader.readNByte(2));
        // 读取父索引
        this.superClassIndex = ByteUtils.bytesToInt(classReader.readNByte(2));
       //  读取接口索引，和接口内容
        readInterfaces(classReader);
        // 读取字段
        fields = MemberInfo.readMembers(classReader,constantPool);
        fieldsCount = fields.length;
        // 读取方法
        methods = MemberInfo.readMembers(classReader,constantPool);
        methodsCount = methods.length;
        // 读取属性
        attributes = AttributeInfo.readAttributeInfos(classReader,constantPool);
        attributesCount = attributes.length;


    }
    public void readInterfaces(ClassReader classReader){
        // 读取 接口数量
        this.interfacesCount = classReader.readNByteToInt(2);
        interfaces = new int [interfacesCount] ;
        for (int i =0 ;i<interfacesCount;i++){
            interfaces[i]=classReader.readNByteToInt(2);
        }
    }


    public String getThisClassName(){
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfos()[thisClassIndex];
        return  this.constantPool.getConstantPoolUtf8Value(constantClassInfo.nameIndex);
    }

    public String getSuperClassName(){
        // 如果索引superClassIndex为0 代表为object类所以直接返回空
        if(superClassIndex>0){
            ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfos()[superClassIndex];
            return  this.constantPool.getConstantPoolUtf8Value(constantClassInfo.nameIndex);
        }else {
            return "";
        }

    }

    public String[] getInterfaceNames() {
        String [] interfaceNames = new String[interfaces.length];
        for(int i =0;i<interfaces.length;i++){
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;

    }

    public String getSourceFile() {
        for (AttributeInfo info : attributes) {
            if (info instanceof SourceFileAttribute) {
                return ((SourceFileAttribute) info).getSourceFileValue();
            }
        }
        return "unknow";
    }




    public String getMagic() {
        return magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }


    @Override
    public String toString() {
        return "ClassFile{" +
                "magic='" + magic + '\'' +
                ", minorVersion=" + minorVersion +
                ", majorVersion=" + majorVersion +
                ", constantPool=" + constantPool +
                ", accessFlags=" + accessFlags +
                ", thisClassIndex=" + thisClassIndex +
                ", superClassIndex=" + superClassIndex +
                ", interfacesCount=" + interfacesCount +
                ", interfaces=" + Arrays.toString(interfaces) +
                ", fields=" + Arrays.toString(fields) +
                ", methods=" + Arrays.toString(methods) +
                ", attributes=" + Arrays.toString(attributes) +
                '}';
    }

}
