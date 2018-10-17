package com.shuframework.myorm.binding;

import com.shuframework.myorm.enums.SqlMethodEnum;
import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.session.impl.DefaultSqlSession;
import com.shuframework.test.model.SysUser;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by shu on 2018/10/17.
 */
public class MapperProxy<T> implements InvocationHandler {
//    private T target;

//    private  MySqlSession sqlSession;
//    private final Class<T> mapperInterface;
//
//    public MapperProxy(MySqlSession sqlSession, Class<T> mapperInterface) {
//        this.sqlSession = sqlSession;
//        this.mapperInterface = mapperInterface;
//    }

//    public MapperProxy(){}

    // 生成某个接口的mapper
    public static <T> T newInstance(Class<T> clazz) {
        MapperProxy<T> proxy = new MapperProxy<>();
        T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
        return t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //object 或接口 的方法其实可以不用关注
//        try {
//            if (Object.class.equals(method.getDeclaringClass())) {
//                return method.invoke(this, args);
//            } else if (isDefaultMethod(method)) {
//                return invokeDefaultMethod(proxy, method, args);
//            }
//        } catch (Throwable t) {
//            t.fillInStackTrace();
//        }
//        System.out.println("===========");
        String methodName = method.getName();
        System.out.println("method:" + methodName);
//        System.out.println("args:" + args[0]);
        Object obj = null;
        if (methodName.equals(SqlMethodEnum.SELECT_BY_ID.getMethod())){
            MySqlSession sqlSession = new DefaultSqlSession();
            obj = sqlSession.selectById((Class) args[0], (Number) args[1]);
        }
        return obj;
    }


    private boolean isDefaultMethod(Method method) {
        return ((method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
                && method.getDeclaringClass().isInterface();
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor
                .newInstance(declaringClass,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }
}
