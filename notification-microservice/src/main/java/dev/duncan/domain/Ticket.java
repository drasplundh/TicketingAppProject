package dev.duncan.domain;

import java.time.LocalDate;

public class Ticket {

    private int ticketId;
    private String title;
    private String department;
    private int amount;
    private Priority priority;
    private String description;
    private LocalDate due;

    private LocalDate createdOn;
    private boolean approved;
    private int createdBy;


    public Ticket() {
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int approvedBy) {
        this.createdBy = approvedBy;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getId() {
        return ticketId;
    }

    public void setId(int id) {
        this.ticketId = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return due;
    }

    public void setDate(LocalDate due) {
        this.due = due;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", amount=" + amount +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", due=" + due +
                ", approved=" + approved +
                ", createdBy=" + createdBy +
                '}';
    }
}
