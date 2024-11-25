package com.mbc.cook.entity.faq;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="FAQ")
@SequenceGenerator(name="faqnum", sequenceName = "faq_seq", allocationSize = 1, initialValue = 1)
public class FaqEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faqnum")
    long faqnum;

    @Column
    String question;

    @Column
    String asked;
}
