package com.atguigu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() {
        // 创建一个SaxReader输入流，去读取 xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();

        Document document = null;
        try {
            document = saxReader.read("src/books.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(document);
    }

    @Test
    public void test2() throws DocumentException {

        // 1. 读取book.xml文件
        SAXReader reader = new SAXReader();

        // 在Junit测试中，相对路径以当前模块名为基准
        Document document = reader.read("src/Books.xml");

        // 2. 通过Document对象获取根元素
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement);

        // 3. 通过根元素获取book标签对象
        // element()和elements()都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");

        // 4. 遍历，将每个book标签转换为Book类的对象
        for (Element book : books) {

            // asXML()：把标签对象转换为标签字符串
            Element nameElement = book.element("name");
//            System.out.println(nameElement.asXML());

            // getText()：获取标签中的文本内容
            String nameText = nameElement.getText();

            // elementText()：直接获取指定标签的文本内容
            String priceText = book.elementText("price");
            String authorText = book.elementText("author");

            String snValue = book.attributeValue("sn");

            System.out.println(new Book(snValue, nameText, Double.parseDouble(priceText), authorText));
        }

    }

}
