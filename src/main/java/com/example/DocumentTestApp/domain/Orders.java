package com.example.DocumentTestApp.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Класс - характеризующий поручения.
 * Аннотация @ManyToOne позволяет создать отношение многие к одному: несколько поручений могут принадлежать
 * одному и тому же пользователю.
 */
@Validated
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooperator_id")
    private Cooperator author;

    @NotBlank(message = "Please add the subject")
    private String subject;
    @NotBlank(message = "Please add the performer")
    private String performer;
    private String deadline;
    private String controlling;
    private String performing;
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Orders() {
    }

    public Orders(String subject, String performer,
                  String deadline, String controlling,
                  String performing, String text, Cooperator cooperator) {
        this.subject = subject;
        this.performer = performer;
        this.deadline = deadline;
        this.controlling = controlling;
        this.performing = performing;
        this.text = text;
        this.author = cooperator;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getControlling() {
        return controlling;
    }

    public void setControlling(String controlling) {
        this.controlling = controlling;
    }

    public String getPerforming() {
        return performing;
    }

    public void setPerforming(String performing) {
        this.performing = performing;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Cooperator getAuthor() {
        return author;
    }

    public void setAuthor(Cooperator author) {
        this.author = author;
    }
}
