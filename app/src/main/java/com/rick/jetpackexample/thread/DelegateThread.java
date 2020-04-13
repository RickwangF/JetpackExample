package com.rick.jetpackexample.thread;

import android.util.Log;

import java.util.List;

public class DelegateThread implements Runnable {

    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (tickets != null && tickets.size() > 0) {
            Ticket ticket = tickets.get(tickets.size() - 1);
            tickets.remove(ticket);
            Log.e("SimpleThread", Thread.currentThread().getName() + "已经卖掉第" + ticket.getNumber() + "张票");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
