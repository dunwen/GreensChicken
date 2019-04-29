package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;

public class AssignExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    return null;
  }

  private Object assign(AssignExpr expr) {
    try {
      FieldAccessExpr fieldAccessExpr = (FieldAccessExpr) Executors.execute(expr.getTarget());
      if (fieldAccessExpr == null) {
        return "can not find assign target";
      }
      String name = fieldAccessExpr.getField();
      Object scope = Executors.execute(fieldAccessExpr.getScope());
      if (scope == null) {
        scope = LibContext.getInstance().getCurrentActivity();
      }
    } catch (Exception e) {
      return "assign fail";
    }
    return null;
  }
}
