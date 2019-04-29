package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.StringLiteralExpr;

public class StringLiteralExprExecutor implements Executor {
  @Override public String execute(Node node) {
    StringLiteralExpr expr = (StringLiteralExpr) node;
    return expr.getValue();
  }
}
