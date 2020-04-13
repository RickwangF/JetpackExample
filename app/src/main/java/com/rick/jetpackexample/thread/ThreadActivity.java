package com.rick.jetpackexample.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityThreadBinding;

import java.util.ArrayList;
import java.util.List;

public class ThreadActivity extends AppCompatActivity {

    List<Ticket> mTickets;

    ActivityThreadBinding mBinding;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_thread);



        mTickets = new ArrayList<>();
        for (int i=0; i<100; i++) {
            Ticket ticket = new Ticket();
            ticket.setName("第" + (i+1) + "张车票");
            ticket.setNumber(i+1);
            mTickets.add(ticket);
        }



        mBinding.startBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {
                // Thread
                // SimpleThread threadOne = new SimpleThread("线程1");
                // SimpleThread threadTwo = new SimpleThread("线程2");

                // threadOne.setTickets(mTickets);
                // threadTwo.setTickets(mTickets);

                // threadOne.start();
                // threadTwo.start();

                // Runnable
                // DelegateThread threadOne = new DelegateThread();
                // threadOne.setTickets(mTickets);
                // DelegateThread threadTwo = new DelegateThread();
                // threadTwo.setTickets(mTickets);

                // Thread thread1 = new Thread(threadOne, "线程1");
                // Thread thread2 = new Thread(threadTwo, "线程2");
                // Thread thread3 = new Thread(threadOne, "线程3");

                // thread1.start();
                // thread3.start();

                // Handler Send Message
                //  final SimpleHandler handler = new SimpleHandler();
                //  handler.setMessageHandler(new SimpleHandlerHandleMessage() {
                //      @Override
                //      public void handleHandleMessage(Message msg) {
                //          Log.e("ThreadActivity", "Activity Handle Message current thread is " + Thread.currentThread().getName());
                //          switch (msg.what) {
                //              case 1:
                //                  mBinding.startBtn.setText("线程1" + msg.obj.toString());
                //                  break;
                //              case 2:
                //                  mBinding.startBtn.setText("线程2" + msg.obj.toString());
                //                  break;
                //          }
                //      }
                //  });
                //
                //  SimpleThread threadOne = new SimpleThread("线程1");
                //  threadOne.setTickets(mTickets);
                //  threadOne.setSendMessage(new SimpleThread.SendMessage() {
                //      @Override
                //      public void sendMessage(Message message) {
                //          Log.e("ThreadActivity", "Handler Send Message current thread is " + Thread.currentThread().getName());
                //          handler.sendMessage(message);
                //      }
                //  });
                //
                //  SimpleThread threadTwo = new SimpleThread("线程2");
                //  threadTwo.setTickets(mTickets);
                //  threadTwo.setSendMessage(new SimpleThread.SendMessage() {
                //      @Override
                //      public void sendMessage(Message message) {
                //          Log.e("ThreadActivity", "Handler Send Message current thread is " + Thread.currentThread().getName());
                //          handler.sendMessage(message);
                //      }
                //  });
                //  threadOne.start();
                //  threadTwo.start();

                // Handler 匿名内部类
                //   handler = new Handler() {
                //       @Override
                //       public void handleMessage(@NonNull Message msg) {
                //           Log.e("ThreadActivity", "Activity Handle Message current thread is " + Thread.currentThread().getName());
                //           switch (msg.what) {
                //               case 1:
                //                   mBinding.startBtn.setText("线程1" + msg.obj.toString());
                //                   break;
                //               case 2:
                //                   mBinding.startBtn.setText("线程2" + msg.obj.toString());
                //                   break;
                //           }
                //       }
                //   };
                //
                //   SimpleThread threadOne = new SimpleThread("线程1");
                //   threadOne.setTickets(mTickets);
                //   threadOne.setSendMessage(new SimpleThread.SendMessage() {
                //       @Override
                //       public void sendMessage(Message message) {
                //           Log.e("ThreadActivity", "Handler Send Message current thread is " + Thread.currentThread().getName());
                //           handler.sendMessage(message);
                //       }
                //   });
                //
                //   SimpleThread threadTwo = new SimpleThread("线程2");
                //   threadTwo.setTickets(mTickets);
                //   threadTwo.setSendMessage(new SimpleThread.SendMessage() {
                //       @Override
                //       public void sendMessage(Message message) {
                //           Log.e("ThreadActivity", "Handler Send Message current thread is " + Thread.currentThread().getName());
                //           handler.sendMessage(message);
                //       }
                //   });
                //   threadOne.start();
                //   threadTwo.start();

                // Handler Post
                // final Handler handler = new Handler();
                // new Thread() {
                //     @Override
                //     public void run() {
                //         while (mTickets.size() > 0) {
                //             final Ticket ticket = mTickets.get(mTickets.size() - 1);
                //             mTickets.remove(ticket);
                //
                //             try {
                //                 Thread.sleep(200);
                //             } catch (InterruptedException e) {
                //                 e.printStackTrace();
                //             }
                //
                //             handler.post(new Runnable() {
                //                 @Override
                //                 public void run() {
                //                     Log.e("TheadOne", "Handler Handle Message in Thread " + Thread.currentThread().getName());
                //                     mBinding.startBtn.setText("线程1" + "已经卖掉第" + ticket.getNumber() + "张票");
                //                 }
                //             });
                //         }
                //     }
                // }.start();
                // new Thread() {
                //     @Override
                //     public void run() {
                //         while (mTickets.size() > 0) {
                //             final Ticket ticket = mTickets.get(mTickets.size() - 1);
                //             mTickets.remove(ticket);
                //
                //             try {
                //                 Thread.sleep(200);
                //             } catch (InterruptedException e) {
                //                 e.printStackTrace();
                //             }
                //
                //             handler.post(new Runnable() {
                //                 @Override
                //                 public void run() {
                //                     Log.e("TheadTwo", "Handler Handle Message in Thread " + Thread.currentThread().getName());
                //                     mBinding.startBtn.setText("线程2" + "已经卖掉第" + ticket.getNumber() + "张票");
                //                 }
                //             });
                //         }
                //     }
                // }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
