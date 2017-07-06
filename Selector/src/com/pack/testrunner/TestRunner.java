package com.pack.testrunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.pack.serialize.ObjectWareHouse;

public class TestRunner implements IMethodInterceptor, Serializable {

	private static final long serialVersionUID = 1L;
	public transient List<IMethodInstance> result;
	public ObjectWareHouse owh;
	public String path = null;

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		int test_Start_Index = 0, test_End_Index = 0;
		FileReader fis = null;
		String startstr = null, endstr = null;

		path = System.getProperty("user.dir");
		File file = new File(path + "\\src\\com\\pack\\resource\\config.properties");
		try {
			fis = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		startstr = prop.getProperty("Test_Start_Index");
		endstr = prop.getProperty("Test_End_Index");
		test_Start_Index = Integer.parseInt(startstr);
		test_End_Index = Integer.parseInt(endstr);
		System.out.println("===============================================");
		System.out.println("Test_Start_Count = " + test_Start_Index + " , " + "Test_End_Count = " + test_End_Index
				+ " , Total Tests Selected = " + (test_End_Index - test_Start_Index + 1));
		System.out.println("===============================================\n");
		result = new ArrayList<IMethodInstance>();

		for (IMethodInstance method : methods) {
			Test testmethod = method.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
			int priority = testmethod.priority();
			if (priority >= test_Start_Index && priority <= test_End_Index) {
				result.add(method);
			}
		}
		serializedObjectState();
		try {
			deserialezedObjectState();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("===============================================\n");
		System.out.println("Below Test Cases executed...\n");
		return result;

	}

	public void serializedObjectState() {
		owh = new ObjectWareHouse(result, path);
		try {
			owh.saveObjectState();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deserialezedObjectState() throws ClassNotFoundException, IOException {
		owh.readObjectState();
	}

}
