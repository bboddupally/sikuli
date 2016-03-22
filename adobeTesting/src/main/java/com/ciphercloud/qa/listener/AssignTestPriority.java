package com.ciphercloud.qa.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javassist.*;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AssignTestPriority implements IAnnotationTransformer
{
    static ClassPool s_ClassPool = ClassPool.getDefault(); 

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        annotation.setPriority(getMethodLineNumber(testMethod));
    }
    private int getMethodLineNumber(Method testMethod)
    {
        try
        {
            CtClass cc = s_ClassPool.get(testMethod.getDeclaringClass().getCanonicalName());
            CtMethod methodX = cc.getDeclaredMethod(testMethod.getName());
            return methodX.getMethodInfo().getLineNumber(0);        
        }
        catch(Exception e)
        {
            throw new RuntimeException("Getting of line number of method "+testMethod+" failed", e);
        }
    }
}