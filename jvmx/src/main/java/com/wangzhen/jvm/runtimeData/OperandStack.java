package com.wangzhen.jvm.runtimeData;


import com.wangzhen.jvm.runtimeData.helap.ZObject;

import java.util.Arrays;

public class OperandStack {
    Slot [] slots;
    int size;
    int maxStack;

    @Override
    public String toString() {
        return "OperandStack{" +
                "slots=" + Arrays.toString(slots) +
                ", size=" + size +
                ", maxStack=" + maxStack +
                '}';
    }

    public OperandStack(int maxStack) {
        this.maxStack = maxStack;
        if (maxStack >= 0) {
            slots = new Slot[maxStack];
        } else {
            throw new NullPointerException("maxStack<0");
            //throw new Exception("11");
        }
    }

    public void pushBoolean(boolean val){
        if(val){
            pushInt(1);
        }else {
            pushInt(0);
        }
    }
    public Boolean popBoolean(){

        return popInt()==1;
    }


    public void pushInt(int val){
        Slot slot = new Slot();
        slot.num = val;
        slots[size] = slot;
        size++;
    }

    public int popInt (){
        if(size==0){
            System.out.println("操作数栈为空");
        }
        size--;
        return slots[size].getNum();
    }

    public void pushFLoat(float val){
        pushInt(Float.floatToIntBits(val));
    }

    public float popFloat(){
       return Float.intBitsToFloat(popInt());
    }

    public void pushLong(long val){
        // 低位
        int num1 = (int) val;
        pushInt(num1);
        int num2 = (int) (val>>32);
        pushInt(num2);

    }
    public long popLong() {
        size -= 2;
        int low = slots[size].num;
        long high = slots[size + 1].num;
        //下面的low在和后面的数进行&运算时自动转换为long;
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void pushDouble(double val){
        long valLong = Double.doubleToLongBits(val);
        pushLong(valLong);
    }

    public double popDouble(){
       return Double.longBitsToDouble(popLong());
    }

    public void pushRef(ZObject zObject){
        if(size==maxStack){
            throw new StackOverflowError("操作数栈溢出");
        }
        Slot slot =new Slot();
        slot.ref = zObject;
        slots[size] = slot;
        size++;
    }

    public ZObject popRef(){
        size --;
        return slots[size].ref;
    }

    public void pushSlot(Slot slot){
        slots[size] = slot;
        size++;
    }
    public Slot popSlot(){
        size--;
        return slots[size];
    }

    //新添加的方法,根据参数n,返回操作数栈中的倒数第n个引用;
    public ZObject getRefFromTop(int n) {
        return slots[size - 1 - n].ref;
    }


    public ZObject getRef(int index) {
        return slots[index].ref;
    }

    // 清空操作数栈
    public void clear(){
        size=0;
    }


        public static void main(String[] args) {
        long n= 222222l;
        int low=(int)n;
        int high=(int)n>>1;

        System.out.println(n);
        System.out.println(low);
        System.out.println(high);
        long n2 = ((high&0x00000000ffffffffL)<<32);
        System.out.println(n2);

    }




}
