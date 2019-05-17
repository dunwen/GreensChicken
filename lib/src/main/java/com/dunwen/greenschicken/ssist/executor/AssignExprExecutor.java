package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.dunwen.greenschicken.Utils;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.NameExpr;

public class AssignExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    return assign((AssignExpr) node);
  }

  private Object assign(AssignExpr expr) {
    try {
      NameExpr nameExpr = (NameExpr) expr.getTarget();
      if (nameExpr == null) {
        return "can not find assign target";
      }
      //写值
      final Object execute = Executors.execute(expr.getValue());
      Utils.setVule(nameExpr.getName(),LibContext.getInstance().getCurrentActivity(), execute);
    } catch (Exception e) {
      return "assign fail";
    }
    return "assign succeed";
  }
}
