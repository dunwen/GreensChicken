package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.Utils;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import java.lang.reflect.Constructor;

public class ObjectCreationExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    ObjectCreationExpr objectCreateExpr = (ObjectCreationExpr) node;
    Class clazz = (Class) Executors.execute(objectCreateExpr.getType());
    if (clazz == null) {
      throw new UnsupportedOperationException(
          "can not find class " + objectCreateExpr.getType().toString());
    }
    Object[] args = Utils.executeAll(objectCreateExpr.getArgs());
    Class[] argsClass = Utils.getTypes(args);
    try {
      Constructor constructor = Utils.constructorOrNull(argsClass, clazz);
      return constructor.newInstance(args);
    } catch (Exception e) {
      throw new UnsupportedOperationException(
          "can not create instance for " + objectCreateExpr.getType().toString());
    }
  }
}
