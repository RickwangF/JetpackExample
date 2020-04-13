package com.rick.jetpackexample.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.ActivityAsyncTaskBinding;

public class AsyncTaskActivity extends AppCompatActivity {

    ActivityAsyncTaskBinding mBinding;

    SimpleHandler mHandler;

    AsyncTask<Void, Integer, Boolean> mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_async_task);
        mAsyncTask = new SimpleTask();
        mHandler = new SimpleHandler(mBinding.tvMessage);
        mBinding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAsyncTask.execute();
            }
        });
        mBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAsyncTask.cancel(true);
            }
        });
    }

    private static class SimpleHandler extends Handler{

        private TextView textView;

        SimpleHandler(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            textView.setText(msg.obj.toString());
        }
    }

    private class SimpleTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBinding.tvMessage.setText("任务开始");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                int count = 0;
                while (count < 100) {
                    count++;
                    publishProgress(count);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mBinding.pbProgress.setProgress(values[0]);
            mBinding.tvMessage.setText("任务执行" + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mBinding.tvMessage.setText("任务完成");
            mBinding.pbProgress.setProgress(0);
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2500);
                        Message message = new Message();
                        message.what = 0;
                        message.obj = "任务未开始";
                        mHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread threadOne = new Thread(runnable, "1");
            threadOne.start();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mBinding.tvMessage.setText("任务取消");
            mBinding.pbProgress.setProgress(0);
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2500);
                        Message message = new Message();
                        message.what = 0;
                        message.obj = "任务未开始";
                        mHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
