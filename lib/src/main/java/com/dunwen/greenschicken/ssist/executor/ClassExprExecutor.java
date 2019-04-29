package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.ClassExpr;

public class ClassExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    ClassExpr classExpr = (ClassExpr) node;
    Object result = Executors.execute(classExpr.getType());
    if (result == null) {
      throw new UnsupportedOperationException(
          "can not find class " + classExpr.getType().toString());
    }
    return result;
  }
}
