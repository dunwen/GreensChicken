package com.dunwen.greenschicken.ssist.executor;

import com.dunwen.greenschicken.LibContext;
import com.github.javaparser.ast.Node;

public class ThisExprExecutor implements Executor {
  @Override public Object execute(Node node) {
    return LibContext.getInstance().getCurrentActivity();
  }
}
