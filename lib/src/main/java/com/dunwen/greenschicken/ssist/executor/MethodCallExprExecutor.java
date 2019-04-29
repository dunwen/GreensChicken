package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.dunwen.greenschicken.Utils;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;
import java.lang.reflect.Method;

public class MethodCallExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    MethodCallExpr expr = (MethodCallExpr) node;
    Object scope = expr.getScope() == null ? LibContext.getInstance().getCurrentActivity()
        : Executors.execute(expr.getScope());
    boolean isStaticMethod = scope instanceof Class && scope != Class.class;
    if (scope == null) {
      throw new UnsupportedOperationException("can not find method call scope");
    }
    String name = expr.getName();
    Object[] args = Utils.executeAll(expr.getArgs());
    Class[] argsType = Utils.getTypes(args);
    Method method = Utils.methodOrNull(argsType, name,
        isStaticMethod ? (Class) scope : scope.getClass());
    if (method == null) {
      throw new UnsupportedOperationException(
          "can not find method in " + scope.toString() + " name " + name);
    }
    try {
      Object result = method.invoke(isStaticMethod ? null : scope, args);
      return result == null ? "undefined" : result;
    } catch (Exception e) {
      throw new UnsupportedOperationException(
          "can not call method " + name + " because " + e.getMessage());
    }
  }
}
