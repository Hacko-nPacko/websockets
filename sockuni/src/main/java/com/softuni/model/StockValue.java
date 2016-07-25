package com.softuni.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by niakoi on 28/6/16.
 */
@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
        @Index(name = "codeCreatedDate", columnList = "stock_code,createdDate"),
})
public class StockValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    Long id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private Stock stock;

    private double value;

    @CreatedDate
    @Setter
    DateTime createdDate;

    public StockValue(Stock stock, double value) {
        this.stock = stock;
        this.value = value;
    }

}
