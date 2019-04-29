package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.ExpressionStmt;

public class ExpressionStemExecutor implements Executor {
  @Override public Object execute(Node node) {
    ExpressionStmt stmt = (ExpressionStmt) node;
    return Executors.execute(stmt.getExpression());
  }
}
