package com.softuni.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by niakoi on 27/6/16.
 */
@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Stock {

    @Id
    @NotEmpty
    @Column(length = 10)
    private String code;

    @CreatedDate
    DateTime createdDate;

    public Stock(String code) {
        this.code = code;
    }

}
