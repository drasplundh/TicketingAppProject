package dev.duncan.domain;

import java.time.LocalDate;

public class TicketCreatedInfo {
    private int ticketId;
    private int userId;

    private LocalDate createdOn;

    public TicketCreatedInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserEmail() {
        return userId;
    }

    public void setUserEmail(int userId) {
        this.userId = userId;
    }
}