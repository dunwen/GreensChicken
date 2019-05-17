package com.dunwen.greenschicken.ssist.executor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;

public class Executors {
  public static Executor getExecutor(Node node) {
    if (node instanceof PrimitiveType) {
      return new PrimitiveTypeExecutor();
    } else if (node instanceof VariableDeclarator) {
      return new VariableDeclaratorExecutor();
    } else if (node instanceof VariableDeclaratorId) {
      return new VariableDeclaratorIdExecutor();
    } else if (node instanceof IntegerLiteralExpr) {
      return new IntegerLiteralExprExecutor();
    } else if (node instanceof VariableDeclarationExpr) {
      return new VariableDeclarationExprExecutor();
    } else if (node instanceof ExpressionStmt) {
      return new ExpressionStemExecutor();
    } else if (node instanceof NameExpr) {
      return new NameExprExecutor();
    } else if (node instanceof StringLiteralExpr) {
      return new StringLiteralExprExecutor();
    } else if (node instanceof ClassOrInterfaceType) {
      return new ClassOrInterfaceTypeExecutor();
    } else if (node instanceof ObjectCreationExpr) {
      return new ObjectCreationExprExecutor();
    } else if (node instanceof ThisExpr) {
      return new ThisExprExecutor();
    } else if (node instanceof ReferenceType) {
      return new ReferenceTypeExecutor();
    } else if (node instanceof ClassExpr) {
      return new ClassExprExecutor();
    } else if (node instanceof MethodCallExpr) {
      return new MethodCallExprExecutor();
    } else if (node instanceof FieldAccessExpr) {
      return new FieldAccessExprExecutor();
    } else if (node instanceof AssignExpr){
      return new AssignExprExecutor();
    }
    return null;
  }

  public static Object execute(Node node) {
    Executor executor = getExecutor(node);
    return executor == null ? null : executor.execute(node);
  }
}
