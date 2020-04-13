package com.rick.jetpackexample.thread;

import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

public class SimpleThread extends Thread {

    public interface SendMessage{
        void sendMessage(Message message);
    }

    private SendMessage sendMessage;

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    private List<Ticket> tickets;

    public SimpleThread(@NonNull String name) {
        super(name);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (tickets != null && tickets.size() > 0) {
            if (tickets.size() > 0) {
                Ticket ticket = tickets.get(tickets.size() - 1);
                tickets.remove(ticket);
                Log.e("SimpleThread", getName() + "已经卖掉第" + ticket.getNumber() + "张票");
                Message message = new Message();
                if (getName() == "线程1") {
                    message.what = 1;
                    message.obj = "已经卖掉第" + ticket.getNumber() + "张票";
                } else {
                    message.what = 2;
                    message.obj = "已经卖掉第" + ticket.getNumber() + "张票";
                }

                if (sendMessage != null) {
                    sendMessage.sendMessage(message);
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
