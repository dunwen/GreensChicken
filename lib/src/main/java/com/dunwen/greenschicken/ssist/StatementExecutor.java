package com.dunwen.greenschicken.ssist;

import com.dunwen.greenschicken.ssist.executor.Executors;
import com.github.javaparser.ast.Node;
import java.util.List;

public class StatementExecutor {
  private static int count = 0;

  public static String execute(List<? extends Node> statement, String commend) {
    try {
      StringBuilder message = new StringBuilder();
      for (int i = 0; i < statement.size(); i++) {
        Object result = Executors.execute(statement.get(i));
        message.append(result == null ? "can't parse your commend!" : result);
      }
      return message.toString();
    } catch (Exception e) {
      return e.getMessage().toString();
    }
  }
}
