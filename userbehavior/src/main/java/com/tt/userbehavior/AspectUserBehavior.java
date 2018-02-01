package com.tt.userbehavior;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 标题: 切面处理
 * 描述：1)JoinPoint
 *           java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
 *           Signature getSignature() ：获取连接点的方法签名对象；
 *           java.lang.Object getTarget() ：获取连接点所在的目标对象；
 *           java.lang.Object getThis() ：获取代理对象本身；
 *      2)ProceedingJoinPoint  只是用于Around
 *          ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法：
 *           java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法；
 *           java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：
 *              通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。
 * 作者:T-ao
 * 创建时间：2018/1/31 10:32
 */
@Aspect
public class AspectUserBehavior {

    private final String TAG = "AspectUserBehavior";

    //切面
    //添加这个注解的方法，方法任何名称，任何返回值，任何参数
    @Pointcut("execution(@com.tt.userbehavior.UserEvent * *(..))")
    public void methodWithAnnotationUserEvent(){}

    //先执行这个方法，再执行被注解方法
    @Before("methodWithAnnotationUserEvent()")
    public void hanldeUserEvent(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if(signature instanceof MethodSignature){
            //获取拦截的方法请求参数
            Object[] args = joinPoint.getArgs();
            //获取切入的类
            Object target = joinPoint.getTarget();
            //目标类方法名称
            String name = signature.getName();
            //获取目标类的方法
            Method currentMethod = target.getClass().getMethod(name, ((MethodSignature) signature).getParameterTypes());

            Log.d(TAG, "当前切入的类： "+target.getClass().getSimpleName()+", 方法： "+currentMethod.getName());

            String tag = currentMethod.getAnnotation(UserEvent.class).tag();
            Log.d(TAG, "注解的tag为： " + tag);
        }

    }

    //activity的声明方法
    @After("execution(* android.app.Activity.on*(..))")
    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "aspect::resume:" + joinPoint.getSignature());
    }

}
