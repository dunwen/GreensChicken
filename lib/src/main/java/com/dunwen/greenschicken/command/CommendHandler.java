package com.dunwen.greenschicken.command;

import com.dunwen.greenschicken.ssist.StatementExecutor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommendHandler {

  public static String eval(String commend) {
    commend = commend.trim();
    String result = executeStatement(commend);
    if (result == null) {
      result = executeExpression(commend);
    }
    return result == null ? "not support this type of commend !" : result;
  }

  private static String executeExpression(String commend) {
    List<Expression> nodes = new ArrayList<>();
    try {
      nodes.add(JavaParser.parseExpression(commend));
    } catch (Exception e) {
      return null;
    }
    return StatementExecutor.execute(nodes, commend);
  }

  private static String executeStatement(String commend) {
    List<Statement> statements;
    try {
      statements = JavaParser.parseStatements(commend);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return StatementExecutor.execute(statements, commend);
  }
}
