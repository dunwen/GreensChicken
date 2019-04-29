package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.dunwen.greenschicken.Utils;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;

public class FieldAccessExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    FieldAccessExpr expr = (FieldAccessExpr) node;
    Object scope = expr.getScope() == null ? LibContext.getInstance().getCurrentActivity()
        : Executors.execute(expr.getScope());
    String name = expr.getField();
    if (scope instanceof Class) {
      // 获取静态变量
      return Utils.objectOrNull(name, ((Class) scope));
    } else {
      return Utils.objectOrNull(name, scope);
    }
  }
}
