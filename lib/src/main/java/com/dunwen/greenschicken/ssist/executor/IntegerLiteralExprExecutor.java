package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;

public class IntegerLiteralExprExecutor implements Executor {
  @Override public Integer execute(Node node) {
    IntegerLiteralExpr integerLiteralExpr = (IntegerLiteralExpr) node;
    return Integer.valueOf(integerLiteralExpr.getValue());
  }
}
