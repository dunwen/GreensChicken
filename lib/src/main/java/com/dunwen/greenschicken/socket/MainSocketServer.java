package com.dunwen.greenschicken.socket;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.dunwen.greenschicken.command.CommendHandler.eval;

public class MainSocketServer {
  private static final String TAG = "MainSocketServer";
  private static Handler handler = new Handler(Looper.getMainLooper());
  private static Executor executor = Executors.newCachedThreadPool();

  public static void start() {
    executor.execute((new Runnable() {
      @Override public void run() {
        OutputStream stream = null;
        ServerSocket socket = null;
        BufferedInputStream inputStream = null;
        try {
          Log.i(TAG, "run: ");

          socket = new ServerSocket(10087);
          Socket s = socket.accept();
          inputStream = new BufferedInputStream(s.getInputStream());
          stream = s.getOutputStream();

          int dataLength;
          byte[] bytes = new byte[1024];
          while ((dataLength = inputStream.read(bytes)) != -1) {
            String commend = new String(bytes, 0, dataLength);
            handler.post(new ExecutorRunnable(stream, commend));
            stream.flush();
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          safeClose(stream);
          safeClose(inputStream);
          try {
            socket.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }));
  }

  private static class ExecutorRunnable implements Runnable {
    private OutputStream os;
    private String commend;

    ExecutorRunnable(OutputStream os, String commend) {
      this.os = os;
      this.commend = commend;
    }

    @Override public void run() {
      String msg;
      try {
        msg = eval(commend);
      } catch (Exception e) {
        msg = e.getMessage();
      }
      safePrint(msg, os);
    }
  }

  private static void safeClose(@Nullable Closeable closeable) {
    try {
      closeable.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void safePrint(final String msg, final OutputStream os) {
    executor.execute(new Runnable() {
      @Override public void run() {
        try {
          os.write(msg.getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
