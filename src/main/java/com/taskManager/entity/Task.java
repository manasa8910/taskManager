package com.taskManager.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ASSIGNED; //default

    @Column(nullable = false)
    private boolean completed = false;  //default

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "createdBy_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assignedTo_id", nullable = false)
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setStatus(Status status, User assignedTo) {
        if (!isCompleted()) {
            if (getAssignedTo() != assignedTo) {
                throw new IllegalStateException("Only assignee can change status");
            }
            if (status == Status.IN_PROGRESS) {
                this.status = Status.IN_PROGRESS;
                setUpdatedAt(new Date());
            } else if (status == Status.COMPLETED) {
                if (category.isNeedsApproval()) {
                    throw new IllegalStateException("Task completion requires approval for this category");
                }
                this.status = Status.COMPLETED;
                setCompleted(true);
                setUpdatedAt(new Date());
            } else if (status == Status.SUBMITTED_FOR_APPROVAL) {
                if (!category.isNeedsApproval()) {
                    throw new IllegalStateException("Task completion does not requires approval for this category");
                }
                this.status = Status.SUBMITTED_FOR_APPROVAL;
                setUpdatedAt(new Date());
            }
        }
    }

    public void approveTask(User approver, Boolean approve) {
        if (!isCompleted()) {
            if (getStatus() != Status.SUBMITTED_FOR_APPROVAL) {
                throw new IllegalStateException("Task must be in SUBMITTED_FOR_APPROVAL status to be approved");
            }
            if (!getCreatedBy().equals(approver)) {
                throw new IllegalArgumentException("Only the task creator can approve this task");
            }
            if (approve) {
                setStatus(Status.COMPLETED);
                setCompleted(true);

            } else setStatus(Status.ASSIGNED);
            setUpdatedAt(new Date());
        }
    }
}

