package com.wen.web.lotterysystem.analysis;

/**
 * @author admin
 * @date 2019-1-3 11:38
 */
public class ModelAnalysis {

    /**
     * 1.TODO 开始编码之前先规划和组织代码，项目开始阶段，先确定代码的分层和架构，该分层和架构在一定程度上决定了未来整个项目的代码风格和维护性
     * 对于长期维护，代码架构的设计师意见非诚重要的事，
     * 注，代码架构必须提供可读性和可维护性。
     * <p>
     * 2.TODO 避免大块重复代码，小块也不行
     * 一个非常好的编程习惯是确保为代码创建函数或类，一边又是重用，当编程的过程中多次出现重读代码块时，这是需要提出来作为公用模块。
     */


    /**
     *  List<String> listStr  = new ArrayList<Stirng>(){{
     *    add("0001");
     *    add("0002");
     *    add("0003");
     *  }}
     *  1.尖括号是泛型操作符
     *  2.一对里面无内容的圆括号表明被调用的构造方法无需参数,就是说，被调用的构造器方式是默认或者缺省模式
     *  3.内层花括号显示这个类定义的代码块，其操作为：调用ArrayList类成员的add()方法，先后将字符串 "0001"."0002" "0003",
     *    添加到此列表的尾部。只要调用这个类的构造方法，这个代码块都会被执行一次。
     *  4.删掉所有的花括号： List<String> list = new ArrayList<String>()；或仅删除内花括号：
     *    List<String> list = new ArrayList<String>(){} ； 结果都是: 创建了一个空的（不含字符串元素的）
     *    ArrayList 对象 list。
     *
     *  5.从本质上来说，属于构造了一个内部匿名类，而内部匿名类默认指向当前所在的实例对象，
     *    如果这个内部匿名类被返回到调用层，并被直接引用，则会引发内存泄露。切记！
     *
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {
        ModelEntity modelEntity = new ModelEntity(){
            @Override
            public void init(String name) {
                System.out.println("1111111111111");
                super.init(name);
            }

            @Override
            public String getId() {
                return super.getId();
            }
        };
        modelEntity.init("s");


    }


}















