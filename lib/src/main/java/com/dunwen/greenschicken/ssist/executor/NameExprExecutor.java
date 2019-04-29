package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.dunwen.greenschicken.Utils;
import com.dunwen.greenschicken.ssist.poor.ClassPoor;
import com.dunwen.greenschicken.ssist.poor.VariablePoor;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.NameExpr;

public class NameExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    NameExpr nameExpr = (NameExpr) node;
    Object object = VariablePoor.get(nameExpr.getName());
    if (object == null) {
      object =
          Utils.objectOrNull(nameExpr.getName(), LibContext.getInstance().getCurrentActivity());
    }
    if (object == null) {
      // 可能想要返回一个 class
      object = ClassPoor.find(nameExpr.getName());
    }
    return object;
  }
}
